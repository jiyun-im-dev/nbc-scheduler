package com.jiyun.scheduler.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleUpdateDto {

    private String title;
    private LocalDate date;
    private String content;
    private Boolean status;

}
