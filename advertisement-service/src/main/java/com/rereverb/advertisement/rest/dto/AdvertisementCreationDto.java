package com.rereverb.advertisement.rest.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdvertisementCreationDto {
    private String header;
    private String description;
    private double price;
}
