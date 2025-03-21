package com.jiyun.scheduler.repository;

import com.jiyun.scheduler.dto.ScheduleResponseDto;
import com.jiyun.scheduler.entity.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        // INSERT 쿼리를 직접 작성하지 않고 JdbcTemplate 이용
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule")
                .usingGeneratedKeyColumns("id")
                // cannot insert null 오류 방지
                .usingColumns("title", "date", "content", "username", "password", "status");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", schedule.getTitle());
        parameters.put("date", schedule.getDate());
        parameters.put("content", schedule.getContent());
        parameters.put("username", schedule.getUsername());
        parameters.put("password", schedule.getPassword());
        parameters.put("status", schedule.getStatus());

        // Primary Key (id 컬럼)
        Number key = jdbcInsert.executeAndReturnKey(parameters);

        // 빌더 패턴을 이용해 DTO를 리턴
        return ScheduleResponseDto.builder()
                .id(key.longValue())
                .title(schedule.getTitle())
                .date(schedule.getDate())
                .content(schedule.getContent())
                .username(schedule.getUsername())
                .password(schedule.getPassword())
                .status(schedule.getStatus())
                .build();
    }

}
