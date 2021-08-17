package com.rereverb.advertisement.service;

import com.rereverb.advertisement.mapper.UserMapper;
import com.rereverb.advertisement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
}
