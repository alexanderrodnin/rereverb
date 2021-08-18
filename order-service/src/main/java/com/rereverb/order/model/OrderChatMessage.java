package com.rereverb.order.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderChatMessage {
    private UUID id;
    private UUID orderId;
    private UUID userId;
    private LocalDateTime dateTime;
    private String message;
}
