-- 스키마 생성
CREATE SCHEMA scheduler;

-- 스키마 사용
USE scheduler;

-- 테이블 생성
CREATE TABLE user (
    username VARCHAR(20) PRIMARY KEY COMMENT '유저 ID',
    name VARCHAR(20) NOT NULL COMMENT '이름',
    email VARCHAR(254) UNIQUE COMMENT '이메일',
    dob DATE COMMENT '생년월일',
    phone_number VARCHAR(20) COMMENT '핸드폰 번호'
);

CREATE TABLE schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '스케줄 식별자',
    title VARCHAR(100) COMMENT '제목',
    date DATE COMMENT '예정 날짜',
    content TEXT COMMENT '내용',
    username VARCHAR(20) COMMENT '작성자',
    FOREIGN KEY (username) REFERENCES user(username),
    password VARCHAR(128) COMMENT '비밀번호',
    status BOOLEAN COMMENT '완료 여부',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 날짜/시간',
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 날짜/시간'
);

-- 테이블 삭제
DROP TABLE schedule;

DROP TABLE user;

-- user 더미 데이터 추가
INSERT INTO user (
    username,
    name,
    email,
    dob,
    phone_number
) VALUES (
    'user1',
    'test',
    'test@gmail.com',
    '2000-01-01',
    '010-1234-5678'
);