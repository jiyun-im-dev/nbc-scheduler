package com.jiyun.scheduler.service;

import com.jiyun.scheduler.dto.ScheduleRequestDto;
import com.jiyun.scheduler.dto.ScheduleResponseDto;
import com.jiyun.scheduler.entity.Schedule;
import com.jiyun.scheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        // 요청 DTO의 데이터로 id가 없는 Schedule 객체 생성
        Schedule schedule = Schedule.builder()
                .title(requestDto.getTitle())
                .date(requestDto.getDate())
                .content(requestDto.getContent())
                .username(requestDto.getUsername())
                .status(requestDto.getStatus())
                .build();

        // 리포지토리 저장 로직 호출
        return scheduleRepository.saveSchedule(schedule);
    }

}
