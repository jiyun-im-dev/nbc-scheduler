package com.jiyun.scheduler.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleRequestDto {

    private String title;
    private Date dueDate;
    private String content;
    private String username;
    private Boolean status;

}
