package com.jiyun.scheduler.repository;

import com.jiyun.scheduler.dto.UserResponseDto;
import com.jiyun.scheduler.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserResponseDto saveUser(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", user.getUsername());
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("dob", user.getDob());
        parameters.put("phone_number", user.getPhoneNumber());

        jdbcInsert.execute(parameters);

        //  DTO를 리턴
        return new UserResponseDto(user);
    }

}
