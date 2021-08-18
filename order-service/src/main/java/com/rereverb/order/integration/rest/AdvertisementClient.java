package com.rereverb.order.integration.rest;

import com.rereverb.api.advertisement.rest.dto.AdvertisementCreationDto;
import com.rereverb.api.advertisement.rest.dto.AdvertisementDto;
import com.rereverb.api.advertisement.rest.dto.AdvertisementModifyingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@FeignClient(name = "advertisementClient", url = "http://localhost:8085")
@RequestMapping("/advertisements")
public interface AdvertisementClient {

    @GetMapping
    Collection<AdvertisementDto> advertisements();

    @GetMapping("/my")
    Collection<AdvertisementDto> getForUser(
            @RequestHeader("X-UserUUID") UUID userId
    );

    @GetMapping("{advertisementId}")
    AdvertisementDto getById(
            @PathVariable UUID advertisementId
    );

    @PostMapping
    void createAdvertisement(
            @RequestBody AdvertisementCreationDto advertisementCreation,
            @RequestHeader("X-UserUUID") UUID userId
    );

    @PutMapping("/{advertisementId}")
    void modifyAdvertisement(
            @PathVariable UUID advertisementId,
            @RequestBody AdvertisementModifyingDto advertisementModifying,
            @RequestHeader("X-UserUUID") UUID userId
    );
}