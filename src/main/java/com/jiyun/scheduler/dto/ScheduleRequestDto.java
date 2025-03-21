package com.jiyun.scheduler.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleRequestDto {

    private String title;
    private Date date;
    private String content;
    private String username;
    private String password;
    private Boolean status;

}
