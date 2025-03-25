package com.jiyun.scheduler.entity;

import com.jiyun.scheduler.dto.ScheduleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class Schedule {

    private Long id; // Automatically created by database
    private String title;
    private LocalDate date; // Only date is needed
    private String content;
    private String username; // FK
    private String password;
    private Boolean status; // Complete = true, Not Complete = false
    private Timestamp createdAt; // Both date and time
    private Timestamp updatedAt; // Both date and time

    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.date = requestDto.getDate();
        this.content = requestDto.getContent();
        this.status = requestDto.getStatus();
    }

}
