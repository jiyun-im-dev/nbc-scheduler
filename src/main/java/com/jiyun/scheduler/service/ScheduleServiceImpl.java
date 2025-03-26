package com.jiyun.scheduler.service;

import com.jiyun.scheduler.dto.ScheduleCreateDto;
import com.jiyun.scheduler.dto.ScheduleResponseDto;
import com.jiyun.scheduler.dto.ScheduleUpdateDto;
import com.jiyun.scheduler.entity.Schedule;
import com.jiyun.scheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleCreateDto requestDto) {
        // 요청 DTO의 데이터로 id가 없는 Schedule 객체 생성
        Schedule schedule = Schedule.builder()
                .title(requestDto.getTitle())
                .date(requestDto.getDate())
                .content(requestDto.getContent())
                .username(requestDto.getUsername())
                .password(requestDto.getPassword())
                .status(requestDto.getStatus())
                .build();

        // 리포지토리 저장 로직 호출
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedulesByCondition(String updatedAt, String username) {
        LocalDateTime updatedAtDateTime = null;

        // 파라미터로 들어온 String(updatedAt)을 LocalDateTime 으로 변환
        if (updatedAt != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            updatedAtDateTime = LocalDate.parse(updatedAt, formatter).atStartOfDay();
        }

        return scheduleRepository.findAllSchedulesByCondition(updatedAtDateTime, username);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        try {
            Schedule schedule = scheduleRepository.findScheduleById(id)
                    .orElseThrow(() -> new NoSuchElementException("스케줄이 존재하지 않습니다."));
            return new ScheduleResponseDto(schedule);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateDto updateDto) {
        int rowsAffected = scheduleRepository.updateSchedule(id, updateDto);
        if (rowsAffected == 0) {
            throw new NoSuchElementException("스케줄이 존재하지 않습니다.");
        }
        return new ScheduleResponseDto(scheduleRepository.findScheduleById(id).get());
    }

}
