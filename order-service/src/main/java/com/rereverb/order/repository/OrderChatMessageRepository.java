package com.rereverb.order.repository;

import com.rereverb.order.entity.OrderChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderChatMessageRepository extends JpaRepository<OrderChatMessageEntity, UUID> {
}
