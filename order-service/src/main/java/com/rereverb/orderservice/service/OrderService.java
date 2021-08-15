package com.rereverb.orderservice.service;

import com.rereverb.orderservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private List<Order> repo = new LinkedList<>();

    public Collection<Order> findAll() {
        return repo.stream().collect(Collectors.toUnmodifiableList());
    }

    public void addOrder(Order order) {
        repo.add(order);
    }

    public void deleteOrder(UUID id) {
        repo.stream()
                .filter(order -> id.equals(order.getOrderId()))
                .findAny()
                .ifPresent(toDelete1 -> repo.remove(toDelete1));
    }

}
