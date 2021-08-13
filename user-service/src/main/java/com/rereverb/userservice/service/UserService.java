package com.rereverb.userservice.service;

import com.rereverb.userservice.exception.ForbiddenException;
import com.rereverb.userservice.mapper.UserMapper;
import com.rereverb.userservice.model.User;
import com.rereverb.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<User> getUser(String sessionId) {
        return authService.getUser(sessionId);
    }

    public Optional<User> getUser(String sessionId, UUID id) {
        return authService.getUser(sessionId)
                .map(item -> {
                    if(!item.getId().equals(id)) {
                        throw new ForbiddenException();
                    }
                    return item;
                });
    }

    public void updateUser(String sessionId, User user) {
        authService.getUser(sessionId)
                .ifPresent(item -> {
                    User toSave = user.toBuilder().id(item.getId()).build();
                    userRepository.save(userMapper.map(toSave));
                });
    }

    public void updateUser(String sessionId, UUID id, User user) {
        authService.getUser(sessionId)
                .ifPresent(item -> {
                    if(!item.getId().equals(id)) {
                        throw new ForbiddenException();
                    }
                    User toSave = user.toBuilder().id(id).build();
                    userRepository.save(userMapper.map(toSave));
                });
    }
}
