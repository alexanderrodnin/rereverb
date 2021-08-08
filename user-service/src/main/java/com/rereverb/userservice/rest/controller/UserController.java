package com.rereverb.userservice.rest.controller;

import com.rereverb.userservice.rest.dto.UserDto;
import com.rereverb.userservice.rest.mapper.UserDtoMapper;
import com.rereverb.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(
            @PathVariable UUID id
    ) {
        return userService.getUser(id)
                .map(userDtoMapper::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDto user) {
        userService.createUser(userDtoMapper.map(user));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable UUID id,
            @RequestBody UserDto user
    ) {
        userService.updateUser(id, userDtoMapper.map(user));
        return ResponseEntity.ok().build();
    }
}
