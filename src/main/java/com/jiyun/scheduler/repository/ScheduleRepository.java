package com.jiyun.scheduler.repository;

import com.jiyun.scheduler.dto.ScheduleResponseDto;
import com.jiyun.scheduler.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules();

    List<ScheduleResponseDto> findAllSchedulesByCondition(LocalDateTime updatedAt, String username);

    Optional<Schedule> findScheduleById(Long id);

}
