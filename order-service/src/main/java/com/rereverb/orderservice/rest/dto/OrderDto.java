package com.rereverb.orderservice.rest.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {
    private UUID orderId;
    private UUID buyerId;
    private UUID sellerId;
    private UUID productId;
}
