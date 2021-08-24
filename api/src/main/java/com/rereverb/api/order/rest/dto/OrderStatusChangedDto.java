package com.rereverb.api.order.rest.dto;

import com.rereverb.api.order.enums.OrderStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderStatusChangedDto {
    private UUID orderId;
    private UUID advertisementId;
    private OrderStatus previousStatus;
    private OrderStatus nextStatus;
}
