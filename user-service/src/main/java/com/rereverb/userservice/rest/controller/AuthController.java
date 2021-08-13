package com.rereverb.userservice.rest.controller;

import com.rereverb.userservice.rest.dto.CredentialsDto;
import com.rereverb.userservice.rest.dto.UserDto;
import com.rereverb.userservice.rest.mapper.CredentialsDtoMapper;
import com.rereverb.userservice.rest.mapper.UserDtoMapper;
import com.rereverb.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CredentialsDtoMapper credentialsDtoMapper;
    private final UserDtoMapper userDtoMapper;

    @GetMapping
    private ResponseEntity<Void> auth(
            @CookieValue(value = "session_id", required = false) String sessionId
    ) {

        return sessionId != null && authService.getUser(sessionId).isPresent()
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @RequestBody CredentialsDto credentials,
            HttpServletResponse httpServletResponse
    ) {
        Optional<String> sessionId = authService.login(credentialsDtoMapper.map(credentials));
        if (sessionId.isPresent()) {
            Cookie cookie = new Cookie("session_id", sessionId.get());
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            UUID userId = authService.getUser(sessionId.get()).orElseThrow().getId();
            return ResponseEntity
                    .ok()
                    .header("X-UserUUID", userId.toString())
                    .build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody UserDto user,
            HttpServletResponse httpServletResponse
    ) {
        Optional<String> sessionId = authService.register(userDtoMapper.map(user));
        if (sessionId.isPresent()) {
            Cookie cookie = new Cookie("session_id", sessionId.get());
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            UUID userId = authService.getUser(sessionId.get()).orElseThrow().getId();
            return ResponseEntity
                    .ok()
                    .header("X-UserUUID", userId.toString())
                    .build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
