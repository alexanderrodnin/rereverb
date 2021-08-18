package com.rereverb.advertisement.rest.mapper;

import com.rereverb.advertisement.model.AdvertisementCreation;
import com.rereverb.api.advertisement.rest.dto.AdvertisementCreationDto;
import org.mapstruct.Mapper;

@Mapper
public interface AdvertisementCreationDtoMapper {
    AdvertisementCreationDto map(AdvertisementCreation advertisementCreation);
    AdvertisementCreation map(AdvertisementCreationDto advertisementCreationDto);
}
