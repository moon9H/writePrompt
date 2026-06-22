-- =========================================================
-- data.sql
-- 개발용 더미 데이터
-- 모든 더미 계정의 로그인 비밀번호: 1234
-- =========================================================

-- 사용자 더미 데이터
INSERT INTO User
(email, password, role, nickname, age, gender, profile)
VALUES
('teacher1@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'TEACHER', '김교사', '1990-03-15', 'M', 'teacher1.png'),
('teacher2@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'TEACHER', '이선생', '1988-07-21', 'F', 'teacher2.png'),
('teacher3@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'TEACHER', '박튜터', '1992-11-05', 'M', 'teacher3.png'),

('student1@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'STUDENT', '문학생', '2001-01-10', 'M', 'student1.png'),
('student2@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'STUDENT', '최학생', '2002-04-18', 'F', 'student2.png'),
('student3@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'STUDENT', '정학생', '2000-09-30', 'M', 'student3.png'),
('student4@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'STUDENT', '한학생', '2003-12-02', 'F', 'student4.png'),
('student5@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'STUDENT', '오학생', '2001-06-25', 'M', 'student5.png');


-- 퀴즈 문제 더미 데이터
INSERT INTO Quiz
(user_id, title, image, level)
VALUES
(1, 'Java 변수와 자료형 퀴즈', 'quiz_java_variable.png', 'EASY'),
(1, 'Java 조건문 퀴즈', 'quiz_java_condition.png', 'EASY'),
(1, 'Java 반복문 퀴즈', 'quiz_java_loop.png', 'EASY'),
(1, 'Java 객체지향 퀴즈', 'quiz_java_oop.png', 'NORMAL'),
(1, 'Java 컬렉션 퀴즈', 'quiz_java_collection.png', 'NORMAL'),

(1, 'Spring MVC 구조 퀴즈', 'quiz_spring_mvc.png', 'EASY'),
(1, 'Spring DI 퀴즈', 'quiz_spring_di.png', 'EASY'),
(1, 'Spring Controller 퀴즈', 'quiz_spring_controller.png', 'NORMAL'),
(1, 'Spring Service 계층 퀴즈', 'quiz_spring_service.png', 'NORMAL'),
(1, 'Spring Security 퀴즈', 'quiz_spring_security.png', 'HARD'),

(2, 'MySQL 기본 구조 퀴즈', 'quiz_mysql_basic.png', 'EASY'),
(2, 'MySQL SELECT 퀴즈', 'quiz_mysql_select.png', 'EASY'),
(2, 'MySQL JOIN 퀴즈', 'quiz_mysql_join.png', 'NORMAL'),
(2, 'MySQL 인덱스 퀴즈', 'quiz_mysql_index.png', 'HARD'),
(2, '트랜잭션 개념 퀴즈', 'quiz_transaction.png', 'NORMAL'),

(2, 'REST API 기본 퀴즈', 'quiz_rest_basic.png', 'EASY'),
(2, 'HTTP Method 퀴즈', 'quiz_http_method.png', 'EASY'),
(2, 'HTTP Status Code 퀴즈', 'quiz_status_code.png', 'NORMAL'),
(2, 'JWT 인증 흐름 퀴즈', 'quiz_jwt_flow.png', 'NORMAL'),
(2, 'CORS 개념 퀴즈', 'quiz_cors.png', 'HARD'),

(3, 'Vue 기본 구조 퀴즈', 'quiz_vue_basic.png', 'EASY'),
(3, 'Vue 컴포넌트 퀴즈', 'quiz_vue_component.png', 'EASY'),
(3, 'Vue Router 퀴즈', 'quiz_vue_router.png', 'NORMAL'),
(3, 'Pinia 상태관리 퀴즈', 'quiz_pinia.png', 'NORMAL'),
(3, 'Axios 통신 흐름 퀴즈', 'quiz_axios.png', 'NORMAL');


-- 퀴즈룸 더미 데이터
INSERT INTO QuizRoom
(user_id, title, room_code, state, level, description, solved_cnt, `like`)
VALUES
(1, 'Java 기초 퀴즈방', 'ROOM-JAVA-001', 'OPEN', 'EASY', 'Java 기초 문법을 연습하는 퀴즈방', 12, 8),
(1, 'Java 객체지향 퀴즈방', 'ROOM-JAVA-002', 'OPEN', 'NORMAL', '객체지향 개념을 이미지로 표현하는 퀴즈방', 6, 11),
(1, 'Spring 입문 퀴즈방', 'ROOM-SPRING-001', 'OPEN', 'EASY', 'Spring MVC와 DI를 다루는 입문 퀴즈방', 21, 15),
(1, 'Spring 심화 퀴즈방', 'ROOM-SPRING-002', 'CLOSED', 'HARD', 'Spring Security와 인증 흐름을 다루는 퀴즈방', 4, 3),

(2, 'MySQL 기초 퀴즈방', 'ROOM-MYSQL-001', 'OPEN', 'EASY', 'SQL과 테이블 구조를 연습하는 퀴즈방', 18, 9),
(2, 'MySQL JOIN 퀴즈방', 'ROOM-MYSQL-002', 'OPEN', 'NORMAL', 'JOIN과 관계형 데이터 표현을 연습하는 퀴즈방', 9, 7),
(2, 'REST API 퀴즈방', 'ROOM-REST-001', 'OPEN', 'NORMAL', 'REST API 요청과 응답 흐름을 학습하는 퀴즈방', 15, 13),
(2, 'JWT 인증 퀴즈방', 'ROOM-JWT-001', 'OPEN', 'NORMAL', 'JWT 인증 흐름을 이미지로 표현하는 퀴즈방', 7, 10),

(3, 'Vue 입문 퀴즈방', 'ROOM-VUE-001', 'OPEN', 'EASY', 'Vue 기본 구조와 컴포넌트를 다루는 퀴즈방', 11, 6),
(3, '프론트 통신 퀴즈방', 'ROOM-FRONT-001', 'OPEN', 'NORMAL', 'Axios와 Router 흐름을 연습하는 퀴즈방', 5, 4),
(3, '비공개 테스트 퀴즈방', 'ROOM-PRIVATE-001', 'CLOSED', 'EASY', 'OPEN 상태가 아닌 테스트용 퀴즈방', 0, 0),
(3, '작성 중인 퀴즈방', 'ROOM-DRAFT-001', 'DRAFT', 'NORMAL', '아직 공개하지 않은 퀴즈방', 0, 0);


-- 퀴즈룸 - 퀴즈 연결 데이터
INSERT INTO Quiz_QuizRoom
(quiz_id, quiz_room_id, quiz_order)
VALUES
-- Java 기초 퀴즈방
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),

-- Java 객체지향 퀴즈방
(4, 2, 1),
(5, 2, 2),
(1, 2, 3),

-- Spring 입문 퀴즈방
(6, 3, 1),
(7, 3, 2),
(8, 3, 3),

-- Spring 심화 퀴즈방 CLOSED
(9, 4, 1),
(10, 4, 2),
(19, 4, 3),

-- MySQL 기초 퀴즈방
(11, 5, 1),
(12, 5, 2),
(15, 5, 3),

-- MySQL JOIN 퀴즈방
(13, 6, 1),
(14, 6, 2),
(15, 6, 3),

-- REST API 퀴즈방
(16, 7, 1),
(17, 7, 2),
(18, 7, 3),

-- JWT 인증 퀴즈방
(19, 8, 1),
(20, 8, 2),
(10, 8, 3),

-- Vue 입문 퀴즈방
(21, 9, 1),
(22, 9, 2),
(23, 9, 3),

-- 프론트 통신 퀴즈방
(23, 10, 1),
(24, 10, 2),
(25, 10, 3),

-- 비공개 테스트 퀴즈방
(21, 11, 1),
(22, 11, 2),

-- 작성 중인 퀴즈방
(24, 12, 1),
(25, 12, 2);


-- 퀴즈 결과 더미 데이터
INSERT INTO QuizResult
(user_id, quiz_room_id, score, feedback)
VALUES
(4, 1, 85.33, 'Java 기초 문법의 핵심 요소를 전반적으로 잘 파악했습니다. 변수, 조건문, 반복문에 대한 표현이 안정적이며, 주요 개념을 이미지로 연결하려는 시도가 좋았습니다. 다만 일부 문제에서는 객체 간 관계나 흐름을 조금 더 구체적으로 작성하면 더 좋은 결과를 얻을 수 있습니다.'),
(5, 1, 72.00, 'Java 기초 개념을 어느 정도 이해하고 있지만, 프롬프트의 구체성이 부족한 부분이 있었습니다. 주요 대상과 배경을 명확히 설명하면 생성 이미지가 정답에 더 가까워질 수 있습니다.'),
(6, 1, 91.67, '전체적으로 Java 기초 개념을 매우 잘 표현했습니다. 각 문제의 핵심 요소가 이미지에 잘 반영되었고, 프롬프트 구성도 안정적이었습니다. 앞으로는 세부 배치나 스타일을 조금 더 구체화하면 완성도가 더 높아질 수 있습니다.'),

(4, 3, 78.67, 'Spring MVC와 DI의 기본 흐름을 잘 이해하고 있습니다. 컨트롤러와 서비스의 관계 표현은 좋았지만, 데이터 이동 흐름을 더 명확히 작성하면 더 좋은 결과를 만들 수 있습니다.'),
(5, 3, 64.33, 'Spring의 주요 개념을 일부 반영했지만 전체 구조 표현이 다소 부족했습니다. 프롬프트에 계층 구조와 객체 간 관계를 더 구체적으로 작성하는 연습이 필요합니다.'),
(7, 3, 88.00, 'Spring 입문 개념을 안정적으로 표현했습니다. 주요 구성 요소가 잘 드러났고, 전체적인 이미지 방향도 좋았습니다. 조금 더 구체적인 구도 설명이 추가되면 더 정확한 결과를 얻을 수 있습니다.'),

(4, 5, 81.00, 'MySQL의 기본 개념과 데이터베이스 구조를 잘 표현했습니다. 테이블과 데이터 저장의 느낌이 잘 드러났습니다. 다만 관계형 구조를 더 명확히 나타내면 더욱 좋겠습니다.'),
(6, 5, 59.67, 'MySQL과 관련된 요소는 일부 포함되었지만 핵심 구조 표현이 부족했습니다. 테이블, 컬럼, 데이터 관계를 더 직접적으로 작성하는 것이 필요합니다.'),
(8, 5, 94.00, 'MySQL 기본 구조를 매우 잘 표현했습니다. 데이터 저장, 테이블 구성, 관계의 느낌이 이미지에 잘 반영되었습니다. 세부 스타일만 조금 더 정리하면 더욱 완성도 있는 결과가 될 수 있습니다.'),

(5, 7, 76.33, 'REST API의 요청과 응답 흐름을 전반적으로 잘 표현했습니다. 클라이언트와 서버 관계가 드러났지만, HTTP 메서드나 상태 코드 같은 세부 요소를 추가하면 더 좋습니다.'),
(7, 7, 42.00, 'REST API의 핵심 흐름이 충분히 드러나지 않았습니다. 클라이언트, 서버, 요청, 응답의 관계를 프롬프트에 더 명확히 작성할 필요가 있습니다.'),
(8, 7, 89.67, 'REST API의 흐름과 구성 요소를 매우 잘 표현했습니다. 요청과 응답의 방향성이 명확했고, 전체적인 구조도 안정적이었습니다. 다음에는 데이터 형식까지 더 구체적으로 표현해보면 좋겠습니다.'),

(4, 9, 69.33, 'Vue의 기본 구조는 어느 정도 표현되었지만 컴포넌트 간 관계가 명확하지 않았습니다. 화면 구성, 컴포넌트, 상태 흐름을 더 구체적으로 작성하면 좋겠습니다.'),
(5, 9, 83.00, 'Vue 컴포넌트와 화면 구성의 느낌을 잘 살렸습니다. 주요 요소가 안정적으로 드러났고, 프론트엔드 구조를 이해하고 있다는 점이 보입니다. 라우터와 상태 흐름까지 포함하면 더 좋은 결과가 될 수 있습니다.'),
(6, 10, 74.00, '프론트엔드 통신 흐름을 어느 정도 잘 표현했습니다. Axios와 서버 통신의 방향성은 보이지만, 요청과 응답 데이터의 흐름을 더 구체적으로 작성하면 좋겠습니다.');