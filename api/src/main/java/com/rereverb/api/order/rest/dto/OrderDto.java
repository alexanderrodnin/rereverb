package com.rereverb.api.order.rest.dto;

import com.rereverb.api.order.enums.OrderStatus;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {
    private UUID id;
    private UUID advertisementId;
    private UUID buyerId;
    private OrderStatus status;
    private List<OrderChatMessageDto> orderChatMessages;
}
