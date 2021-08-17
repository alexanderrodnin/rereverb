package com.rereverb.order.service;

import com.rereverb.order.mapper.UserMapper;
import com.rereverb.order.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
}
