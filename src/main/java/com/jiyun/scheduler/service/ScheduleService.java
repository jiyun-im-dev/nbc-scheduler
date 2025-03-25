package com.jiyun.scheduler.service;

import com.jiyun.scheduler.dto.ScheduleRequestDto;
import com.jiyun.scheduler.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAllSchedulesByCondition(String updatedAt, String username);

}
