package com.jiyun.scheduler.dto;

import com.jiyun.scheduler.entity.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class UserResponseDto {

    private String username;
    private String name;
    private String email;
    private Date dob;
    private String phoneNumber;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.email = user.getEmail();
        this.dob = user.getDob();
        this.phoneNumber = user.getPhoneNumber();
    }
}
