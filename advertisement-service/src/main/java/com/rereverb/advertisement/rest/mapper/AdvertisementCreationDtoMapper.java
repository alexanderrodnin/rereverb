package com.rereverb.advertisement.rest.mapper;

import com.rereverb.advertisement.model.Advertisement;
import com.rereverb.advertisement.model.AdvertisementCreation;
import com.rereverb.advertisement.rest.dto.AdvertisementCreationDto;
import com.rereverb.advertisement.rest.dto.AdvertisementDto;
import org.mapstruct.Mapper;

@Mapper
public interface AdvertisementCreationDtoMapper {
    AdvertisementCreationDto map(AdvertisementCreation advertisementCreation);
    AdvertisementCreation map(AdvertisementCreationDto advertisementCreationDto);
}
