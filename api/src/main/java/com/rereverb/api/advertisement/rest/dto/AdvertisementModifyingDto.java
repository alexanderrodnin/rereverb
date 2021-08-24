package com.rereverb.api.advertisement.rest.dto;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdvertisementModifyingDto {
    private String header;
    private String description;
    private double price;
}
