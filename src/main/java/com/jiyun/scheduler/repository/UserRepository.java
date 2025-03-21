package com.jiyun.scheduler.repository;

import com.jiyun.scheduler.dto.UserResponseDto;
import com.jiyun.scheduler.entity.User;

public interface UserRepository {

    UserResponseDto saveUser(User user);

}
