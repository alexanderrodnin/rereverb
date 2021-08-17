package com.rereverb.advertisement.service;

import com.rereverb.advertisement.mapper.AdvertisementMapper;
import com.rereverb.advertisement.repository.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapper advertisementMapper;
}
