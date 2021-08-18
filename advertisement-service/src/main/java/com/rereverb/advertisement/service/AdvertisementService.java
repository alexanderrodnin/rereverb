package com.rereverb.advertisement.service;

import com.rereverb.advertisement.exception.ForbiddenException;
import com.rereverb.advertisement.exception.NotFoundException;
import com.rereverb.advertisement.mapper.AdvertisementMapper;
import com.rereverb.advertisement.model.Advertisement;
import com.rereverb.advertisement.model.AdvertisementCreation;
import com.rereverb.advertisement.model.AdvertisementModifying;
import com.rereverb.advertisement.model.AdvertisementStatus;
import com.rereverb.advertisement.repository.AdvertisementRepository;
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

    public Advertisement getById(UUID advertisementId) {
        return advertisementRepository
                .findById(advertisementId)
                .map(advertisementMapper::map)
                .orElseThrow(() -> new NotFoundException("Advertisement not found"));
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
}
