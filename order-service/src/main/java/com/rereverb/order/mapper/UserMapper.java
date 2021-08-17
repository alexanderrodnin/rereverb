package com.rereverb.order.mapper;

import com.rereverb.order.entity.UserEntity;
import com.rereverb.order.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserEntity map(User user);
    User map(UserEntity user);
}
