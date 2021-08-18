package com.rereverb.order.service;

import com.rereverb.order.entity.OrderEntity;
import com.rereverb.order.exception.NotFoundException;
import com.rereverb.order.mapper.OrderChatMessageMapper;
import com.rereverb.order.mapper.OrderMapper;
import com.rereverb.order.model.Order;
import com.rereverb.order.model.OrderChatMessage;
import com.rereverb.api.order.enums.OrderStatus;
import com.rereverb.order.repository.OrderChatMessageRepository;
import com.rereverb.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderChatMessageRepository orderChatMessageRepository;

    private final OrderMapper orderMapper;
    private final OrderChatMessageMapper orderChatMessageMapper;

    public void createOrder(UUID advertisementId, UUID buyerId, String firstMessage) {
        Order order = Order.builder()
                .advertisementId(advertisementId)
                .buyerId(buyerId)
                .status(OrderStatus.ACTIVE)
                .build();

        OrderEntity savedOrder = orderRepository.save(orderMapper.map(order));
        addChatMessage(savedOrder.getId(), firstMessage,  buyerId);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(item -> orderMapper.map(item, List.of()))
                .collect(Collectors.toList());
    }

    public void addChatMessage(UUID orderId, String message, UUID userId) {
        OrderChatMessage orderChatMessage = OrderChatMessage.builder()
                .orderId(orderId)
                .userId(userId)
                .dateTime(LocalDateTime.now())
                .message(message)
                .build();

        orderChatMessageRepository.save(orderChatMessageMapper.map(orderChatMessage));
    }

    public void changeOrderStatus(UUID orderId, OrderStatus newStatus, UUID userId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found"));
//        if (!userId.equals(orderEntity.getSellerId())) {
//            throw new ForbiddenException();
//        }
        orderEntity.setStatus(newStatus);

        orderRepository.save(orderEntity);
    }
}
