package com.rereverb.order.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private UUID id;
    private UUID advertisementId;
    private UUID buyerId;
    private OrderStatus orderStatus;
    private List<OrderChatMessage> orderChatMessages;
}
