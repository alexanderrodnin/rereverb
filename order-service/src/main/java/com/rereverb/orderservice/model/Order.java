package com.rereverb.orderservice.model;

import lombok.*;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private UUID orderId;
    private UUID buyerId;
    private UUID sellerId;
    private UUID productId;
}
