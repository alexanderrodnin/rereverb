package com.rereverb.userservice.service;

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

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<User> getUser(UUID id) {
        return userRepository.findById(id).map(userMapper::map);
    }

    public void createUser(User user) {
        userRepository.save(userMapper.map(user));
    }

    public void updateUser(UUID id, User user) {
        User toSave = user.toBuilder().id(id).build();
        userRepository.save(userMapper.map(toSave));
    }
}
