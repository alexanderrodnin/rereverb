package com.rereverb.order.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "order_chat_message")
@NoArgsConstructor
@Getter
@Setter
public class OrderChatMessageEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "message")
    private String message;
}
