package com.jiyun.scheduler.service;

import com.jiyun.scheduler.dto.ScheduleRequestDto;
import com.jiyun.scheduler.dto.ScheduleResponseDto;
import com.jiyun.scheduler.entity.Schedule;
import com.jiyun.scheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = Schedule.builder()
                .title(requestDto.getTitle())
                .dueDate(requestDto.getDueDate())
                .content(requestDto.getContent())
                .username(requestDto.getUsername())
                .status(requestDto.getStatus())
                .build();
        return scheduleRepository.saveSchedule(schedule);
    }

}
