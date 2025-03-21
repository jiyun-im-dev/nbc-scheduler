package com.jiyun.scheduler.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class UserRequestDto {

    private String username;
    private String name;
    private String email;
    private Date dob;
    private String phoneNumber;

}
