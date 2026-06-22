CREATE DATABASE IF NOT EXISTS wp_db;
USE wp_db;

-- 기존 테이블 삭제용
DROP TABLE IF EXISTS QuizResult;
DROP TABLE IF EXISTS Quiz_QuizRoom;
DROP TABLE IF EXISTS QuizRoom;
DROP TABLE IF EXISTS Quiz;
DROP TABLE IF EXISTS User;

-- 사용자
CREATE TABLE User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL,
    role VARCHAR(128) NOT NULL,
    nickname VARCHAR(128) NOT NULL,
    age DATE NOT NULL,
    gender VARCHAR(128) NOT NULL,
    profile VARCHAR(128) NOT NULL,
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 퀴즈 문제
CREATE TABLE Quiz (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(128) NOT NULL,
    image VARCHAR(128) NOT NULL,
    level VARCHAR(128) NOT NULL,
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_quiz_user
        FOREIGN KEY (user_id)
        REFERENCES User(id)
        ON DELETE CASCADE
);

-- 퀴즈룸
CREATE TABLE QuizRoom (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(128) NOT NULL,
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    room_code VARCHAR(128) NOT NULL UNIQUE,
    state VARCHAR(128) NOT NULL,
    level VARCHAR(128) NOT NULL,
    description VARCHAR(128) NOT NULL,
    solved_cnt BIGINT NOT NULL DEFAULT 0,
    `like` BIGINT NOT NULL DEFAULT 0,

    CONSTRAINT fk_quizroom_user
        FOREIGN KEY (user_id)
        REFERENCES User(id)
        ON DELETE CASCADE
);

-- 퀴즈 - 퀴즈룸 연결 테이블
CREATE TABLE Quiz_QuizRoom (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quiz_id BIGINT NOT NULL,
    quiz_room_id BIGINT NOT NULL,
    quiz_order BIGINT NOT NULL,

    CONSTRAINT fk_quiz_quizroom_quiz
        FOREIGN KEY (quiz_id)
        REFERENCES Quiz(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_quiz_quizroom_quizroom
        FOREIGN KEY (quiz_room_id)
        REFERENCES QuizRoom(id)
        ON DELETE CASCADE
);

-- 퀴즈 결과
CREATE TABLE QuizResult (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    quiz_room_id BIGINT NOT NULL,
    score DECIMAL(5, 2) NOT NULL,
    feedback TEXT NOT NULL,
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_quizresult_user
        FOREIGN KEY (user_id)
        REFERENCES User(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_quizresult_quizroom
        FOREIGN KEY (quiz_room_id)
        REFERENCES QuizRoom(id)
        ON DELETE CASCADE
);