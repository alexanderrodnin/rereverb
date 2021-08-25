package com.rereverb.order.rest.controller;

import com.rereverb.api.order.enums.OrderStatus;
import com.rereverb.api.order.rest.dto.OrderCreationDto;
import com.rereverb.api.order.rest.dto.OrderDto;
import com.rereverb.api.order.rest.dto.OrderStatusChangeRequestDto;
import com.rereverb.api.user.model.SessionKey;
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
            @CookieValue("session_id") String sessionId
    ) {
        SessionKey key = SessionKey.fromBase64(sessionId);
        orderService.createOrder(
                orderCreationDto.getAdvertisementId(),
                key.getUserId(),
                orderCreationDto.getMessage()
        );
    }

    @PostMapping("/{orderId}/chat")
    public void addComment(
            @PathVariable UUID orderId,
            @RequestBody String message,
            @CookieValue("session_id") String sessionId
    ) {
        SessionKey key = SessionKey.fromBase64(sessionId);
        orderService.addChatMessage(orderId, message, key.getUserId());
    }

    @PostMapping("/status")
    public void changeOrderStatus(
            @RequestBody OrderStatusChangeRequestDto orderStatusChangeRequestDto,
            @CookieValue("session_id") String sessionId
    ) {
        SessionKey key = SessionKey.fromBase64(sessionId);
        orderService.changeOrderStatus(
                orderStatusChangeRequestDto.getOrderId(),
                orderStatusChangeRequestDto.getStatus(),
                key.getUserId()
        );
    }

    @GetMapping("/status")
    public String getStatus() {
        return "it's status";
    }
}
