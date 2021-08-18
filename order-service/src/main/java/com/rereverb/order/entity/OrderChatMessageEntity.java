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

    @Column("order_id")
    private UUID orderId;

    @Column("user_id")
    private UUID userId;

    @Column("date_time")
    private LocalDateTime dateTime;

    @Column("message")
    private String message;
}
