package com.rereverb.advertisement.model;

import lombok.*;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdvertisementCreation {
    private String header;
    private String description;
    private double price;
}
