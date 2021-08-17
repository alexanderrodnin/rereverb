package com.rereverb.advertisement.rest.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdvertisementDto {
    private UUID id;
    private String header;
    private String description;
    private double price;
}
