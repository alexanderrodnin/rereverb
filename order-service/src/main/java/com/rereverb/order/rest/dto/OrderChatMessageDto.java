package com.rereverb.order.rest.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderChatMessageDto {
    private UUID id;
    private UUID orderId;
    private LocalDateTime dateTime;
    private String message;
}
