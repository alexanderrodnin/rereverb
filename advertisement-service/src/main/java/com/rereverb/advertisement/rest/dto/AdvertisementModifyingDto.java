package com.rereverb.advertisement.rest.dto;

import com.rereverb.advertisement.model.AdvertisementStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdvertisementModifyingDto {
    private String header;
    private String description;
    private double price;
}
