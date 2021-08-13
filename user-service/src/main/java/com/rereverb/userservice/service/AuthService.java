package com.rereverb.userservice.service;

import com.rereverb.userservice.mapper.UserMapper;
import com.rereverb.userservice.model.Credentials;
import com.rereverb.userservice.model.User;
import com.rereverb.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private Map<String, User> sessions = new ConcurrentHashMap<>();

    public Optional<User> getUser(String sessionId) {
        return Optional.ofNullable(sessions.get(sessionId));
    }

    public Optional<String> login(Credentials credentials) {
        return userRepository.findByNameAndPassword(credentials.getName(), credentials.getPassword())
                .map(userMapper::map)
                .map(item -> {
                    String sessionId = RandomString.make();
                    sessions.put(sessionId, item);
                    return sessionId;
                });
    }

    public Optional<String> register(User user) {
        return Optional.of(userRepository.save(userMapper.map(user)))
                .map(userMapper::map)
                .map(item -> {
                    String sessionId = RandomString.make();
                    sessions.put(sessionId, item);
                    return sessionId;
                });
    }
}
