package com.rereverb.order.mapper;

import com.rereverb.order.entity.OrderChatMessageEntity;
import com.rereverb.order.model.OrderChatMessage;
import org.mapstruct.Mapper;

@Mapper
public interface OrderChatMessageMapper {
    OrderChatMessageEntity map(OrderChatMessage orderChatMessage);
    OrderChatMessage map(OrderChatMessageEntity orderChatMessageEntity);
}
