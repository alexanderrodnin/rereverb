package com.rereverb.order.rest.controller;

import com.rereverb.api.advertisement.rest.dto.AdvertisementDto;
import com.rereverb.order.integration.rest.AdvertisementClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    public final AdvertisementClient advertisementClient;

    @GetMapping
    public Collection<AdvertisementDto> testMe() {
        return advertisementClient.advertisements();
    }
}
