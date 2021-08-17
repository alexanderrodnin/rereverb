package com.rereverb.advertisement.mapper;

import com.rereverb.advertisement.entity.UserEntity;
import com.rereverb.advertisement.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserEntity map(User user);
    User map(UserEntity user);
}
