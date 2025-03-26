package com.jiyun.scheduler.service;

import com.jiyun.scheduler.dto.ScheduleCreateDto;
import com.jiyun.scheduler.dto.ScheduleResponseDto;
import com.jiyun.scheduler.dto.ScheduleUpdateDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleCreateDto requestDto);

    List<ScheduleResponseDto> findAllSchedulesByCondition(String updatedAt, String username);

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, String password, ScheduleUpdateDto updateDto);
}
