package com.rereverb.userservice.mapper;

import com.rereverb.userservice.entity.UserEntity;
import com.rereverb.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserEntity map(User user);
    User map(UserEntity user);
}
