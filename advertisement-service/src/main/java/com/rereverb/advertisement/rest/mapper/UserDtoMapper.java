package com.rereverb.advertisement.rest.mapper;

import com.rereverb.advertisement.model.User;
import com.rereverb.advertisement.rest.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserDtoMapper {
    UserDto map(User user);
    User map(UserDto user);
}
