package com.jiyun.scheduler.service;

import com.jiyun.scheduler.dto.ScheduleRequestDto;
import com.jiyun.scheduler.dto.ScheduleResponseDto;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);

}
