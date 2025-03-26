package com.jiyun.scheduler.repository;

import com.jiyun.scheduler.dto.ScheduleResponseDto;
import com.jiyun.scheduler.dto.ScheduleUpdateDto;
import com.jiyun.scheduler.entity.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcTemplateScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
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

        // 빌더 패턴을 이용해 DTO 를 리턴
        return ScheduleResponseDto.builder()
                .id(key.longValue())
                .title(schedule.getTitle())
                .date(schedule.getDate())
                .content(schedule.getContent())
                .username(schedule.getUsername())
                .status(schedule.getStatus())
                .createdAt(schedule.getCreatedAt())
                .updatedAt(schedule.getUpdatedAt())
                .build();
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return jdbcTemplate.query("SELECT * FROM schedule", scheduleDtoRowMapper());
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedulesByCondition(LocalDateTime updatedAt, String username) {
        StringBuilder sql = new StringBuilder("SELECT * FROM schedule WHERE 1 = 1");

        // 동적 조건 추가
        Map<String, Object> paramMap = new HashMap<>();
        if (updatedAt != null) {
            sql.append(" AND updated_at BETWEEN :updatedAt AND NOW()");
            paramMap.put("updatedAt", updatedAt);
        }
        if (username != null && !username.isEmpty()) {
            sql.append(" AND username = :username");
            paramMap.put("username", username);
        }
        sql.append(" ORDER BY updated_at DESC");

        // SQL 실행
        return namedParameterJdbcTemplate.query(sql.toString(), paramMap, scheduleDtoRowMapper());
    }

    // RowMapper<T>가 함수형 인터페이스이므로 익명 클래스 대신 람다식 사용
    private RowMapper<ScheduleResponseDto> scheduleDtoRowMapper() {
        return (rs, rowNum) -> new ScheduleResponseDto(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getDate("date").toLocalDate(),
                rs.getString("content"),
                rs.getString("username"),
                rs.getBoolean("status"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at")
        );
    }

    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        String sql = "SELECT * FROM schedule WHERE ID = :id";

        List<Schedule> result = namedParameterJdbcTemplate.query(sql, paramMap, scheduleRowMapper());

        return result.stream().findAny();
    }

    private RowMapper<Schedule> scheduleRowMapper() {
        return (rs, rowNum) -> new Schedule(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getDate("date").toLocalDate(),
                rs.getString("content"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getBoolean("status"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at")
        );
    }

    @Override
    public int updateSchedule(Long id, ScheduleUpdateDto updateDto) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("title", updateDto.getTitle());
        paramMap.put("date", updateDto.getDate());
        paramMap.put("content", updateDto.getContent());
        paramMap.put("status", updateDto.getStatus());
        String sql = "UPDATE schedule " +
                "SET title = :title, date = :date, content = :content, status = :status " +
                "WHERE ID = :id";

        int rowsAffected = namedParameterJdbcTemplate.update(sql, paramMap);
        return rowsAffected;
    }

}
