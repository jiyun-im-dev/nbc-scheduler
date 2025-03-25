package com.jiyun.scheduler.controller;

import com.jiyun.scheduler.dto.ScheduleRequestDto;
import com.jiyun.scheduler.dto.ScheduleResponseDto;
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
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
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

//    @PutMapping("/{id}")
//    public ResponseEntity<ScheduleResponseDto> updateSchedule(
//            @PathVariable Long id,
//            @RequestBody ScheduleRequestDto requestDto) {
//        // TODO: DB 에서 id로 스케줄을 찾아옴, Validation 필요
//        // Schedule schedule = DB에서 찾아온 데이터
//
//        schedule.update(requestDto);
//
//        return new ScheduleResponseDto(schedule);
//    }
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
