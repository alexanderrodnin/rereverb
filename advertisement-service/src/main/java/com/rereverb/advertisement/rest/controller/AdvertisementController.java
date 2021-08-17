package com.rereverb.advertisement.rest.controller;

import com.rereverb.advertisement.rest.mapper.AdvertisementDtoMapper;
import com.rereverb.advertisement.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("advertisements")
@RequiredArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    private final AdvertisementDtoMapper advertisementDtoMapper;


}
