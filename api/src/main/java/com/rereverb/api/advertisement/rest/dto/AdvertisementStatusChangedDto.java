package com.rereverb.api.advertisement.rest.dto;

import com.rereverb.api.advertisement.enums.AdvertisementStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdvertisementStatusChangedDto {
    private UUID advertisementId;
    private AdvertisementStatus previousStatus;
    private AdvertisementStatus nextStatus;
}
