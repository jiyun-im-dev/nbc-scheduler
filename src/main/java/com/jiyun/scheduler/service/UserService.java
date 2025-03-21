package com.jiyun.scheduler.service;

import com.jiyun.scheduler.dto.UserRequestDto;
import com.jiyun.scheduler.dto.UserResponseDto;

public interface UserService {

    UserResponseDto saveUser(UserRequestDto requestDto);

}
