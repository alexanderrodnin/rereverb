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
public class OrderCreationDto {
    private UUID advertisementId;
    private String message;
}
