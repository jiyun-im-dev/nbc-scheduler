package com.jiyun.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class User {

    private String username;
    private String name;
    private String email;
    private String password;
    private Date dateOfBirth; // Only date is needed
    private String phoneNumber;

}
