package com.rereverb.order.rest.dto;

import com.rereverb.order.model.OrderChatMessage;
import com.rereverb.order.model.OrderStatus;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {
    private UUID id;
    private UUID advertisementId;
    private UUID buyerId;
    private OrderStatus orderStatus;
    private List<OrderChatMessage> orderChatMessages;
}
