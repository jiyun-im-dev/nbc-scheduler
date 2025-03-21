package com.jiyun.scheduler.controller;

import com.jiyun.scheduler.dto.ScheduleRequestDto;
import com.jiyun.scheduler.dto.ScheduleResponseDto;
import com.jiyun.scheduler.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping
//    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
//        // TODO: DB에서 아이디로 스케줄을 찾아서 Schedule 객체를 생성
//
//        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
//    }
//
//    @GetMapping
//    public List<ScheduleResponseDto> findAllSchedules() {
//        List<ScheduleResponseDto> scheduleList = new ArrayList<>();
//        // TODO: DB에서 모든 스케줄을 찾아서 스케줄 리스트에 넣기
//        return scheduleList;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ScheduleResponseDto> updateSchedule(
//            @PathVariable Long id,
//            @RequestBody ScheduleRequestDto requestDto) {
//        // TODO: DB에서 id로 스케줄을 찾아옴, Validation 필요
//        // Schedule schedule = DB에서 찾아온 데이터
//
//        schedule.update(requestDto);
//
//        return new ScheduleResponseDto(schedule);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
//        // TODO: DB에서 ID에 해당하는 스케줄 삭제
//
//        return new ResponseEntity<>(HttpStatus.SEE_OTHER); // 삭제 성공
//
//        // TODO: 해당하는 스케줄이 없으면 NOT_FOUND를 반환
//    }

}
