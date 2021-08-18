package com.rereverb.order.mapper;

import com.rereverb.order.entity.OrderChatMessageEntity;
import com.rereverb.order.entity.OrderEntity;
import com.rereverb.order.model.Order;
import com.rereverb.order.model.OrderChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
  imports = OrderChatMessageMapper.class
)
public interface OrderMapper {

    OrderEntity map(Order order);

    @Mapping(target = "orderChatMessages", source = "orderChatMessagesEntities")
    Order map(OrderEntity orderEntity, List<OrderChatMessageEntity> orderChatMessagesEntities);
}
