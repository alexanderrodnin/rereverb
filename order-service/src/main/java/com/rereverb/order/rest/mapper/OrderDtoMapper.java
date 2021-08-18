package com.rereverb.order.rest.mapper;

import com.rereverb.order.model.Order;
import com.rereverb.api.order.rest.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrderDtoMapper {
    OrderDto map(Order order);
    Order map(OrderDto orderDto);
}
