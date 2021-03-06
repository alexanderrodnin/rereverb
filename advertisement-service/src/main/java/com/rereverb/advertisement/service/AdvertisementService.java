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
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapper advertisementMapper;
    private final KafkaProducer kafkaProducer;
    private final Counter advCreationCounter;

    public AdvertisementService(
            AdvertisementRepository advertisementRepository,
            AdvertisementMapper advertisementMapper,
            KafkaProducer kafkaProducer,
            MeterRegistry registry
    ) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementMapper = advertisementMapper;
        this.kafkaProducer = kafkaProducer;
        advCreationCounter = Counter
                .builder("rereverb_advertisement_creation_counter")
                .description("Number of created advertisements")
                .register(registry);
    }

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
        Advertisement advertisement = Advertisement.builder()
                .userId(userId)
                .header(advertisementCreation.getHeader())
                .description(advertisementCreation.getDescription())
                .price(advertisementCreation.getPrice())
                .status(AdvertisementStatus.OPENED)
                .build();

        advertisementRepository.save(advertisementMapper.map(advertisement));
        advCreationCounter.increment();
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
