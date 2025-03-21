package com.jiyun.scheduler.service;

import com.jiyun.scheduler.dto.UserRequestDto;
import com.jiyun.scheduler.dto.UserResponseDto;
import com.jiyun.scheduler.entity.User;
import com.jiyun.scheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto requestDto) {
        // 요청 DTO의 데이터로 User 객체 생성 (왜 이렇게 하는지 잘 모르겠음)
        User user = User.builder()
                .username(requestDto.getUsername())
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .dob(requestDto.getDob())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();

        return userRepository.saveUser(user);
    }

}
