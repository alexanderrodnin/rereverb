package com.rereverb.advertisement.rest.controller;

import com.rereverb.api.advertisement.enums.AdvertisementStatus;
import com.rereverb.api.advertisement.rest.dto.AdvertisementCreationDto;
import com.rereverb.api.advertisement.rest.dto.AdvertisementDto;
import com.rereverb.api.advertisement.rest.dto.AdvertisementModifyingDto;
import com.rereverb.advertisement.rest.mapper.AdvertisementCreationDtoMapper;
import com.rereverb.advertisement.rest.mapper.AdvertisementDtoMapper;
import com.rereverb.advertisement.rest.mapper.AdvertisementModifyingDtoMapper;
import com.rereverb.advertisement.service.AdvertisementService;
import com.rereverb.api.advertisement.rest.dto.AdvertisementStatusChangedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/advertisements")
@RequiredArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    private final AdvertisementDtoMapper advertisementDtoMapper;
    private final AdvertisementCreationDtoMapper advertisementCreationDtoMapper;
    private final AdvertisementModifyingDtoMapper advertisementModifyingDtoMapper;

    @GetMapping
    public Collection<AdvertisementDto> advertisements() {
        return advertisementService.getAll().stream()
                .map(advertisementDtoMapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping("/my")
    public Collection<AdvertisementDto> getForUser(
            @RequestHeader("X-UserUUID") UUID userId
    ) {
        return advertisementService.getForUser(userId).stream()
                .map(advertisementDtoMapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping("{advertisementId}")
    public AdvertisementDto getById(
            @PathVariable UUID advertisementId
    ) {
        return advertisementDtoMapper.map(
                advertisementService.getById(advertisementId)
        );
    }

    @PostMapping
    public void createAdvertisement(
            @RequestBody AdvertisementCreationDto advertisementCreation,
            @RequestHeader("X-UserUUID") UUID userId
    ) {
        advertisementService.createAdvertisement(
                advertisementCreationDtoMapper.map(advertisementCreation), userId
        );
    }

    @PutMapping("/{advertisementId}")
    public void modifyAdvertisement(
            @PathVariable UUID advertisementId,
            @RequestBody AdvertisementModifyingDto advertisementModifying,
            @RequestHeader("X-UserUUID") UUID userId
    ) {
        advertisementService.modifyAdvertisement(
                advertisementId,
                advertisementModifyingDtoMapper.map(advertisementModifying),
                userId
        );
    }
//
//    @PutMapping("/{advertisementId}/status")
//    public AdvertisementStatusChangedDto modifyAdvertisementStatus(
//            @PathVariable UUID advertisementId,
//            @RequestBody AdvertisementStatus nextStatus,
//            @RequestHeader("X-UserUUID") UUID userId
//    ) {
//        AdvertisementStatus previousStatus = advertisementService.modifyAdvertisementStatus(advertisementId, nextStatus);
//        return AdvertisementStatusChangedDto.builder()
//                .advertisementId(advertisementId)
//                .previousStatus(previousStatus)
//                .nextStatus(nextStatus)
//                .build();
//    }

}
