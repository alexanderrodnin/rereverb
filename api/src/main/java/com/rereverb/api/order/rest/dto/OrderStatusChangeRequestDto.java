package com.rereverb.api.order.rest.dto;

import com.rereverb.api.order.enums.OrderStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderStatusChangeRequestDto {
    private UUID orderId;
    private OrderStatus status;
}
