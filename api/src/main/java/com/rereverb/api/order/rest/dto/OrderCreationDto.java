package com.rereverb.api.order.rest.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCreationDto {
    private UUID advertisementId;
    private String message;
}
