package com.jiyun.scheduler.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleCreateDto {

    private String title;
    private LocalDate date;
    private String content;
    private String username;
    private String password;
    private Boolean status;

}
