package com.rereverb.order.entity;

import com.rereverb.order.model.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "order")
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column("advertisement_id")
    private UUID advertisementId;

    @Column("buyer_id")
    private UUID buyerId;

    @Column("order_status")
    private OrderStatus orderStatus;
}
