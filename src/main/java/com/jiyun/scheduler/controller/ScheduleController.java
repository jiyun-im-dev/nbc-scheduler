package com.jiyun.scheduler.controller;

import com.jiyun.scheduler.dto.ScheduleCreateDto;
import com.jiyun.scheduler.dto.ScheduleResponseDto;
import com.jiyun.scheduler.dto.ScheduleUpdateDto;
import com.jiyun.scheduler.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleCreateDto requestDto) {
        ScheduleResponseDto responseDto = scheduleService.saveSchedule(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedulesByCondition(
            // 쿼리 스트링으로 조건을 받음
            @RequestParam(required = false) String updatedAt,
            @RequestParam(required = false) String username) {
        return scheduleService.findAllSchedulesByCondition(updatedAt, username);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        ScheduleResponseDto responseDto = scheduleService.findScheduleById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestParam String password,
            @RequestBody ScheduleUpdateDto updateDto) {
        ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, password, updateDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
//        // TODO: DB 에서 ID에 해당하는 스케줄 삭제
//
//        return new ResponseEntity<>(HttpStatus.SEE_OTHER); // 삭제 성공
//
//        // TODO: 해당하는 스케줄이 없으면 NOT_FOUND 를 반환
//    }

}
