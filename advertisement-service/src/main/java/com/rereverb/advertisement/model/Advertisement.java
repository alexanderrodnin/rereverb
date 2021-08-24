package com.rereverb.advertisement.model;

import com.rereverb.api.advertisement.enums.AdvertisementStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Advertisement {
    private UUID id;
    private UUID userId;
    private String header;
    private String description;
    private double price;
    private AdvertisementStatus status;
}
