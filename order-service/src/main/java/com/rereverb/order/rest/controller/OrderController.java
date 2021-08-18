package com.rereverb.order.rest.controller;

import com.rereverb.order.model.OrderStatus;
import com.rereverb.order.rest.dto.OrderCreationDto;
import com.rereverb.order.rest.dto.OrderDto;
import com.rereverb.order.rest.mapper.OrderDtoMapper;
import com.rereverb.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderDtoMapper orderDtoMapper;

    @GetMapping("/my")
    public List<OrderDto> getOrders() {
        return orderService.getOrders().stream()
                .map(orderDtoMapper::map)
                .collect(Collectors.toList());
    }

    @PostMapping
    void createOrder(
            @RequestBody OrderCreationDto orderCreationDto,
            @RequestHeader("X-UserUUID") UUID userId
    ) {
        orderService.createOrder(
                orderCreationDto.getAdvertisementId(),
                userId,
                orderCreationDto.getMessage()
        );
    }

    @PostMapping("/{orderId}/chat")
    public void addComment(
            @PathVariable UUID orderId,
            @RequestBody String message,
            @RequestHeader("X-UserUUID") UUID userId
    ) {
        orderService.addChatMessage(orderId, message, userId);
    }

    @PostMapping("/{orderId}/chat")
    public void changeOrderStatus(
            @PathVariable UUID orderId,
            @RequestBody OrderStatus orderStatus,
            @RequestHeader("X-UserUUID") UUID userId
    ) {
        orderService.changeOrderStatus(orderId, orderStatus, userId);
    }
}
