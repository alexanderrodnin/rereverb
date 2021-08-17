package com.rereverb.advertisement.rest.mapper;

import com.rereverb.advertisement.model.Advertisement;
import com.rereverb.advertisement.rest.dto.AdvertisementDto;
import org.mapstruct.Mapper;

@Mapper
public interface AdvertisementDtoMapper {
    AdvertisementDto map(Advertisement advertisement);
    Advertisement map(AdvertisementDto advertisementDto);
}
