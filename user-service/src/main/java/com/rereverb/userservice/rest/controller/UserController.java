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

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(
            @CookieValue("session_id") String sessionId
    ) {
        return userService.getUser(sessionId)
                .map(userDtoMapper::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(
            @PathVariable UUID id,
            @CookieValue("session_id") String sessionId
    ) {
        return userService.getUser(sessionId, id)
                .map(userDtoMapper::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/me")
    public ResponseEntity<Void> updateUser(
            @RequestBody UserDto user,
            @CookieValue("session_id") String sessionId
    ) {
        userService.updateUser(sessionId, userDtoMapper.map(user));
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable UUID id,
            @RequestBody UserDto user,
            @CookieValue("session_id") String sessionId
    ) {
        userService.updateUser(sessionId, id, userDtoMapper.map(user));
        return ResponseEntity.ok().build();
    }
}
