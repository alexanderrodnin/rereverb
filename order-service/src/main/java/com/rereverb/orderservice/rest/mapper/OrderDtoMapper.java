package com.rereverb.orderservice.rest.mapper;

import com.rereverb.orderservice.model.Order;
import com.rereverb.orderservice.rest.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrderDtoMapper {
    OrderDto map(Order order);
    Order map(OrderDto order);
}
