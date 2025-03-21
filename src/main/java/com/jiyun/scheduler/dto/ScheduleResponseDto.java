package com.jiyun.scheduler.dto;

import com.jiyun.scheduler.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class ScheduleResponseDto {

    private Long id;
    private String title;
    private Date date;
    private String content;
    private String username;
    private String password;
    private Boolean status;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.date = schedule.getDate();
        this.content = schedule.getContent();
        this.username = schedule.getUsername();
        this.status = schedule.getStatus();
    }

}
