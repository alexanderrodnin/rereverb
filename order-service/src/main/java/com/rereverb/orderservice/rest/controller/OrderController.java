package com.rereverb.orderservice.rest.controller;

import com.rereverb.orderservice.rest.dto.OrderDto;
import com.rereverb.orderservice.rest.mapper.OrderDtoMapper;
import com.rereverb.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderDtoMapper orderDtoMapper;


    @GetMapping
    public Collection<OrderDto> getAll() {
        return orderService.findAll()
                .stream()
                .map(orderDtoMapper::map)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addOrder(@RequestBody OrderDto body) {
        orderService.addOrder(
                orderDtoMapper.map(
                        body.toBuilder().orderId(UUID.randomUUID()).build()
                )
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);
    }
}
