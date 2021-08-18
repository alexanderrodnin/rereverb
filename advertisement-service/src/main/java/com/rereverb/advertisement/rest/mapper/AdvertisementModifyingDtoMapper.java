package com.rereverb.advertisement.rest.mapper;

import com.rereverb.advertisement.model.AdvertisementModifying;
import com.rereverb.api.advertisement.rest.dto.AdvertisementModifyingDto;
import org.mapstruct.Mapper;

@Mapper
public interface AdvertisementModifyingDtoMapper {
    AdvertisementModifyingDto map(AdvertisementModifying advertisementModifying);
    AdvertisementModifying map(AdvertisementModifyingDto advertisementModifyingDto);
}
