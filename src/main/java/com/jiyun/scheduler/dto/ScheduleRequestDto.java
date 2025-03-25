package com.jiyun.scheduler.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class ScheduleRequestDto {

    private String title;
    private LocalDate date;
    private String content;
    private String username;
    private String password;
    private Boolean status;

}
