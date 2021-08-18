package com.rereverb.advertisement.rest.dto;

import lombok.*;

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
