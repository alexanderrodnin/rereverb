package com.rereverb.order.model;

import com.rereverb.api.order.enums.OrderStatus;
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
    private OrderStatus status;
    private List<OrderChatMessage> orderChatMessages;
}
