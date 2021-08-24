package com.rereverb.order.service;

import com.rereverb.api.advertisement.enums.AdvertisementStatus;
import com.rereverb.api.advertisement.rest.dto.AdvertisementDto;
import com.rereverb.api.advertisement.rest.dto.AdvertisementStatusChangedDto;
import com.rereverb.api.order.enums.OrderStatus;
import com.rereverb.api.order.rest.dto.OrderStatusChangedDto;
import com.rereverb.order.entity.OrderEntity;
import com.rereverb.order.exception.ForbiddenException;
import com.rereverb.order.exception.NotFoundException;
import com.rereverb.order.integration.kafka.KafkaProducer;
import com.rereverb.order.integration.rest.AdvertisementClient;
import com.rereverb.order.mapper.OrderMapper;
import com.rereverb.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderStatusSagaService {

    private final OrderRepository orderRepository;
    private final AdvertisementClient advertisementClient;

    public final KafkaProducer kafkaProducer;

    public void execute(UUID orderId, OrderStatus newStatus, UUID userId) {
        OrderEntity orderEntity = getOrderEntity(orderId);

        AdvertisementDto advertisementDto = advertisementClient.getById(orderEntity.getAdvertisementId());
        if (!userId.equals(advertisementDto.getUserId())) {
            throw new ForbiddenException();
        }

        OrderStatus previousOrderStatus = orderEntity.getStatus();
        orderEntity.setStatus(newStatus);
        orderRepository.save(orderEntity);

        kafkaProducer.publishAdvertisementStatusChanged(
                OrderStatusChangedDto.builder()
                        .orderId(orderId)
                        .advertisementId(orderEntity.getAdvertisementId())
                        .previousStatus(previousOrderStatus)
                        .nextStatus(newStatus)
                        .build()
        );
    }

    public void handleAdvertisementStatusChanged(AdvertisementStatusChangedDto dto) {
        if (dto.getNextStatus().equals(AdvertisementStatus.CLOSED)) {
            // finish saga
            // do nothing because OrderEntity in finish state
        } else {
            // rollback saga
            OrderEntity entity = getOrderEntityByAdvertisementID(dto.getAdvertisementId());
            entity.setStatus(OrderStatus.ACTIVE);
        }
    }

    private OrderEntity getOrderEntity(UUID orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));
    }

    private OrderEntity getOrderEntityByAdvertisementID(UUID advertisementId) {
        return orderRepository
                .findByAdvertisementId(advertisementId)
                .orElseThrow(() -> new NotFoundException("Order not found"));
    }


}
