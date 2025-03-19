package com.jiyun.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id; // Automatically created by database
    private String title;
    private Date dueDate; // Only date is needed
    private String content;
    private String username; // FK
    private Boolean status; // Complete = true, Not Complete = false
    private Date createdAt; // Both date and time
    private Date updatedAt; // Both date and time



}
