package com.rereverb.order.rest.mapper;

import com.rereverb.order.model.User;
import com.rereverb.order.rest.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserDtoMapper {
    UserDto map(User user);
    User map(UserDto user);
}
