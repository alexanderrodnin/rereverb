package com.rereverb.userservice.rest.mapper;

import com.rereverb.userservice.model.User;
import com.rereverb.userservice.rest.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserDtoMapper {
    UserDto map(User user);
    User map(UserDto user);
}
