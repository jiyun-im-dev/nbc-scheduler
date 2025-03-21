package com.jiyun.scheduler.repository;

import com.jiyun.scheduler.dto.ScheduleResponseDto;
import com.jiyun.scheduler.entity.Schedule;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

}
