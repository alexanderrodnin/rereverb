package com.rereverb.advertisement.mapper;

import com.rereverb.advertisement.entity.AdvertisementEntity;
import com.rereverb.advertisement.model.Advertisement;
import org.mapstruct.Mapper;

@Mapper
public interface AdvertisementMapper {
    AdvertisementEntity map(Advertisement advertisement);
    Advertisement map(AdvertisementEntity advertisementEntity);
}
