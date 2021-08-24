package com.rereverb.advertisement.service;

import com.rereverb.advertisement.exception.ForbiddenException;
import com.rereverb.advertisement.exception.NotFoundException;
import com.rereverb.advertisement.integration.kafka.KafkaProducer;
import com.rereverb.advertisement.mapper.AdvertisementMapper;
import com.rereverb.advertisement.model.Advertisement;
import com.rereverb.advertisement.model.AdvertisementCreation;
import com.rereverb.advertisement.model.AdvertisementModifying;
import com.rereverb.advertisement.repository.AdvertisementRepository;
import com.rereverb.api.advertisement.enums.AdvertisementStatus;
import com.rereverb.api.advertisement.rest.dto.AdvertisementStatusChangedDto;
import com.rereverb.api.order.enums.OrderStatus;
import com.rereverb.api.order.rest.dto.OrderStatusChangedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapper advertisementMapper;
    private final KafkaProducer kafkaProducer;

    public Collection<Advertisement> getAll() {
        return advertisementRepository.findAll().stream()
                .map(advertisementMapper::map)
                .collect(Collectors.toList());
    }

    public Collection<Advertisement> getForUser(UUID userId) {
        return advertisementRepository.findByUserId(userId).stream()
                .map(advertisementMapper::map)
                .collect(Collectors.toList());
    }

    public Advertisement getById(UUID id) {
        return advertisementRepository
                .findById(id)
                .map(advertisementMapper::map)
                .orElseThrow(() -> new NotFoundException("Advertisement not found"));
    }

    public Collection<Advertisement> getByIds(Iterable<UUID> ids) {
        return advertisementRepository
                .findAllById(ids)
                .stream()
                .map(advertisementMapper::map)
                .collect(Collectors.toList());
    }

    public void createAdvertisement(
            AdvertisementCreation advertisementCreation,
            UUID userId
    ) {
        Advertisement.builder()
                .userId(userId)
                .header(advertisementCreation.getHeader())
                .description(advertisementCreation.getDescription())
                .price(advertisementCreation.getPrice())
                .status(AdvertisementStatus.OPENED)
                .build();
    }

    public void modifyAdvertisement(
            UUID advertisementId,
            AdvertisementModifying advertisementModifying,
            UUID userId
    ) {
        Advertisement advertisement = getById(advertisementId);

        if (!userId.equals(advertisement.getUserId())) {
            throw new ForbiddenException(
                    "Modification of advisement with id = " + advertisementId.toString() + " is restricted for current iser"
            ) ;
        }

        Advertisement modified = advertisement.toBuilder()
                .header(advertisement.getHeader())
                .description(advertisementModifying.getDescription())
                .price(advertisementModifying.getPrice())
                .build();

        advertisementRepository.save(advertisementMapper.map(modified));
    }

    public void handleOrderStatusChanged(OrderStatusChangedDto orderStatusChangedDto) {
        AdvertisementStatus nextStatus =
                orderStatusChangedDto.getNextStatus().equals(OrderStatus.SUCCESS)
                        || orderStatusChangedDto.getNextStatus().equals(OrderStatus.CANCELLED)
                ? AdvertisementStatus.CLOSED : AdvertisementStatus.OPENED;

        modifyAdvertisementStatus(orderStatusChangedDto.getAdvertisementId(), nextStatus);
    }

    public AdvertisementStatus modifyAdvertisementStatus(
            UUID advertisementId,
            AdvertisementStatus newStatus
    ) {
        Advertisement advertisement = getById(advertisementId);
        AdvertisementStatus statusBeforeModification = advertisement.getStatus();

        advertisement.setStatus(newStatus);

        advertisementRepository.save(advertisementMapper.map(advertisement));

        kafkaProducer.publishAdvertisementStatusChanged(
                AdvertisementStatusChangedDto.builder()
                        .advertisementId(advertisementId)
                        .previousStatus(statusBeforeModification)
                        .nextStatus(newStatus)
                        .build()
        );

        return statusBeforeModification;
    }
}
