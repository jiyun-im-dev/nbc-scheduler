package com.jiyun.scheduler.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class User {

    private String username;
    private String name;
    private String email;
    private Date dob; // Date of Birth - Only date is needed
    private String phoneNumber;

}
