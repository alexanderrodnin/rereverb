package com.rereverb.api.order.rest.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderChatMessageDto {
    private UUID id;
    private UUID orderId;
    private UUID userId;
    private LocalDateTime dateTime;
    private String message;
}
