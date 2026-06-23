/*
Legacy seed data below is disabled because it contains broken encoding and invalid SQL strings.
The active seed data starts after this block comment.

-- =========================================================
-- data.sql
-- ê°œë°œ???”ë? ?°ì´??
-- ëª¨ë“  ?”ë? ê³„ì •??ë¡œê·¸??ë¹„ë?ë²ˆí˜¸: 1234
-- =========================================================

-- ?¬ìš©???”ë? ?°ì´??
INSERT INTO User
(email, password, role, nickname, age, gender, profile)
VALUES
('teacher1@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'TEACHER', 'ê¹€êµì‚¬', '1990-03-15', 'M', 'teacher1.png'),
('teacher2@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'TEACHER', '?´ì„ ??, '1988-07-21', 'F', 'teacher2.png'),
('teacher3@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'TEACHER', 'ë°•íŠœ??, '1992-11-05', 'M', 'teacher3.png'),

('student1@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'STUDENT', 'ë¬¸í•™??, '2001-01-10', 'M', 'student1.png'),
('student2@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'STUDENT', 'ìµœí•™??, '2002-04-18', 'F', 'student2.png'),
('student3@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'STUDENT', '?•í•™??, '2000-09-30', 'M', 'student3.png'),
('student4@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'STUDENT', '?œí•™??, '2003-12-02', 'F', 'student4.png'),
('student5@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'STUDENT', '?¤í•™??, '2001-06-25', 'M', 'student5.png');


-- ?´ì¦ˆ ë¬¸ì œ ?”ë? ?°ì´??
INSERT INTO Quiz
(user_id, title, image, level)
VALUES
(1, 'Java ë³€?˜ì? ?ë£Œ???´ì¦ˆ', 'quiz_java_variable.png', 'EASY'),
(1, 'Java ì¡°ê±´ë¬??´ì¦ˆ', 'quiz_java_condition.png', 'EASY'),
(1, 'Java ë°˜ë³µë¬??´ì¦ˆ', 'quiz_java_loop.png', 'EASY'),
(1, 'Java ê°ì²´ì§€???´ì¦ˆ', 'quiz_java_oop.png', 'NORMAL'),
(1, 'Java ì»¬ë ‰???´ì¦ˆ', 'quiz_java_collection.png', 'NORMAL'),

(1, 'Spring MVC êµ¬ì¡° ?´ì¦ˆ', 'quiz_spring_mvc.png', 'EASY'),
(1, 'Spring DI ?´ì¦ˆ', 'quiz_spring_di.png', 'EASY'),
(1, 'Spring Controller ?´ì¦ˆ', 'quiz_spring_controller.png', 'NORMAL'),
(1, 'Spring Service ê³„ì¸µ ?´ì¦ˆ', 'quiz_spring_service.png', 'NORMAL'),
(1, 'Spring Security ?´ì¦ˆ', 'quiz_spring_security.png', 'HARD'),

(2, 'MySQL ê¸°ë³¸ êµ¬ì¡° ?´ì¦ˆ', 'quiz_mysql_basic.png', 'EASY'),
(2, 'MySQL SELECT ?´ì¦ˆ', 'quiz_mysql_select.png', 'EASY'),
(2, 'MySQL JOIN ?´ì¦ˆ', 'quiz_mysql_join.png', 'NORMAL'),
(2, 'MySQL ?¸ë±???´ì¦ˆ', 'quiz_mysql_index.png', 'HARD'),
(2, '?¸ëœ??…˜ ê°œë… ?´ì¦ˆ', 'quiz_transaction.png', 'NORMAL'),

(2, 'REST API ê¸°ë³¸ ?´ì¦ˆ', 'quiz_rest_basic.png', 'EASY'),
(2, 'HTTP Method ?´ì¦ˆ', 'quiz_http_method.png', 'EASY'),
(2, 'HTTP Status Code ?´ì¦ˆ', 'quiz_status_code.png', 'NORMAL'),
(2, 'JWT ?¸ì¦ ?ë¦„ ?´ì¦ˆ', 'quiz_jwt_flow.png', 'NORMAL'),
(2, 'CORS ê°œë… ?´ì¦ˆ', 'quiz_cors.png', 'HARD'),

(3, 'Vue ê¸°ë³¸ êµ¬ì¡° ?´ì¦ˆ', 'quiz_vue_basic.png', 'EASY'),
(3, 'Vue ì»´í¬?ŒíŠ¸ ?´ì¦ˆ', 'quiz_vue_component.png', 'EASY'),
(3, 'Vue Router ?´ì¦ˆ', 'quiz_vue_router.png', 'NORMAL'),
(3, 'Pinia ?íƒœê´€ë¦??´ì¦ˆ', 'quiz_pinia.png', 'NORMAL'),
(3, 'Axios ?µì‹  ?ë¦„ ?´ì¦ˆ', 'quiz_axios.png', 'NORMAL');


-- ?´ì¦ˆë£??”ë? ?°ì´??
INSERT INTO QuizRoom
(user_id, title, room_code, state, level, description, solved_cnt, `like`)
VALUES
(1, 'Java ê¸°ì´ˆ ?´ì¦ˆë°?, 'ROOM-JAVA-001', 'OPEN', 'EASY', 'Java ê¸°ì´ˆ ë¬¸ë²•???°ìŠµ?˜ëŠ” ?´ì¦ˆë°?, 12, 8),
(1, 'Java ê°ì²´ì§€???´ì¦ˆë°?, 'ROOM-JAVA-002', 'OPEN', 'NORMAL', 'ê°ì²´ì§€??ê°œë…???´ë?ì§€ë¡??œí˜„?˜ëŠ” ?´ì¦ˆë°?, 6, 11),
(1, 'Spring ?…ë¬¸ ?´ì¦ˆë°?, 'ROOM-SPRING-001', 'OPEN', 'EASY', 'Spring MVC?€ DIë¥??¤ë£¨???…ë¬¸ ?´ì¦ˆë°?, 21, 15),
(1, 'Spring ?¬í™” ?´ì¦ˆë°?, 'ROOM-SPRING-002', 'CLOSED', 'HARD', 'Spring Security?€ ?¸ì¦ ?ë¦„???¤ë£¨???´ì¦ˆë°?, 4, 3),

(2, 'MySQL ê¸°ì´ˆ ?´ì¦ˆë°?, 'ROOM-MYSQL-001', 'OPEN', 'EASY', 'SQLê³??Œì´ë¸?êµ¬ì¡°ë¥??°ìŠµ?˜ëŠ” ?´ì¦ˆë°?, 18, 9),
(2, 'MySQL JOIN ?´ì¦ˆë°?, 'ROOM-MYSQL-002', 'OPEN', 'NORMAL', 'JOINê³?ê´€ê³„í˜• ?°ì´???œí˜„???°ìŠµ?˜ëŠ” ?´ì¦ˆë°?, 9, 7),
(2, 'REST API ?´ì¦ˆë°?, 'ROOM-REST-001', 'OPEN', 'NORMAL', 'REST API ?”ì²­ê³??‘ë‹µ ?ë¦„???™ìŠµ?˜ëŠ” ?´ì¦ˆë°?, 15, 13),
(2, 'JWT ?¸ì¦ ?´ì¦ˆë°?, 'ROOM-JWT-001', 'OPEN', 'NORMAL', 'JWT ?¸ì¦ ?ë¦„???´ë?ì§€ë¡??œí˜„?˜ëŠ” ?´ì¦ˆë°?, 7, 10),

(3, 'Vue ?…ë¬¸ ?´ì¦ˆë°?, 'ROOM-VUE-001', 'OPEN', 'EASY', 'Vue ê¸°ë³¸ êµ¬ì¡°?€ ì»´í¬?ŒíŠ¸ë¥??¤ë£¨???´ì¦ˆë°?, 11, 6),
(3, '?„ë¡ ???µì‹  ?´ì¦ˆë°?, 'ROOM-FRONT-001', 'OPEN', 'NORMAL', 'Axios?€ Router ?ë¦„???°ìŠµ?˜ëŠ” ?´ì¦ˆë°?, 5, 4),
(3, 'ë¹„ê³µê°??ŒìŠ¤???´ì¦ˆë°?, 'ROOM-PRIVATE-001', 'CLOSED', 'EASY', 'OPEN ?íƒœê°€ ?„ë‹Œ ?ŒìŠ¤?¸ìš© ?´ì¦ˆë°?, 0, 0),
(3, '?‘ì„± ì¤‘ì¸ ?´ì¦ˆë°?, 'ROOM-DRAFT-001', 'DRAFT', 'NORMAL', '?„ì§ ê³µê°œ?˜ì? ?Šì? ?´ì¦ˆë°?, 0, 0);


-- ?´ì¦ˆë£?- ?´ì¦ˆ ?°ê²° ?°ì´??
INSERT INTO Quiz_QuizRoom
(quiz_id, quiz_room_id, quiz_order)
VALUES
-- Java ê¸°ì´ˆ ?´ì¦ˆë°?
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),

-- Java ê°ì²´ì§€???´ì¦ˆë°?
(4, 2, 1),
(5, 2, 2),
(1, 2, 3),

-- Spring ?…ë¬¸ ?´ì¦ˆë°?
(6, 3, 1),
(7, 3, 2),
(8, 3, 3),

-- Spring ?¬í™” ?´ì¦ˆë°?CLOSED
(9, 4, 1),
(10, 4, 2),
(19, 4, 3),

-- MySQL ê¸°ì´ˆ ?´ì¦ˆë°?
(11, 5, 1),
(12, 5, 2),
(15, 5, 3),

-- MySQL JOIN ?´ì¦ˆë°?
(13, 6, 1),
(14, 6, 2),
(15, 6, 3),

-- REST API ?´ì¦ˆë°?
(16, 7, 1),
(17, 7, 2),
(18, 7, 3),

-- JWT ?¸ì¦ ?´ì¦ˆë°?
(19, 8, 1),
(20, 8, 2),
(10, 8, 3),

-- Vue ?…ë¬¸ ?´ì¦ˆë°?
(21, 9, 1),
(22, 9, 2),
(23, 9, 3),

-- ?„ë¡ ???µì‹  ?´ì¦ˆë°?
(23, 10, 1),
(24, 10, 2),
(25, 10, 3),

-- ë¹„ê³µê°??ŒìŠ¤???´ì¦ˆë°?
(21, 11, 1),
(22, 11, 2),

-- ?‘ì„± ì¤‘ì¸ ?´ì¦ˆë°?
(24, 12, 1),
(25, 12, 2);


-- ?´ì¦ˆ ê²°ê³¼ ?”ë? ?°ì´??
INSERT INTO QuizResult
(user_id, quiz_room_id, score, feedback)
VALUES
(4, 1, 85.33, 'Java ê¸°ì´ˆ ë¬¸ë²•???µì‹¬ ?”ì†Œë¥??„ë°˜?ìœ¼ë¡????Œì•…?ˆìŠµ?ˆë‹¤. ë³€?? ì¡°ê±´ë¬? ë°˜ë³µë¬¸ì— ?€???œí˜„???ˆì •?ì´ë©? ì£¼ìš” ê°œë…???´ë?ì§€ë¡??°ê²°?˜ë ¤???œë„ê°€ ì¢‹ì•˜?µë‹ˆ?? ?¤ë§Œ ?¼ë? ë¬¸ì œ?ì„œ??ê°ì²´ ê°?ê´€ê³„ë‚˜ ?ë¦„??ì¡°ê¸ˆ ??êµ¬ì²´?ìœ¼ë¡??‘ì„±?˜ë©´ ??ì¢‹ì? ê²°ê³¼ë¥??»ì„ ???ˆìŠµ?ˆë‹¤.'),
(5, 1, 72.00, 'Java ê¸°ì´ˆ ê°œë…???´ëŠ ?•ë„ ?´í•´?˜ê³  ?ˆì?ë§? ?„ë¡¬?„íŠ¸??êµ¬ì²´?±ì´ ë¶€ì¡±í•œ ë¶€ë¶„ì´ ?ˆì—ˆ?µë‹ˆ?? ì£¼ìš” ?€?ê³¼ ë°°ê²½??ëª…í™•???¤ëª…?˜ë©´ ?ì„± ?´ë?ì§€ê°€ ?•ë‹µ????ê°€ê¹Œì›Œì§????ˆìŠµ?ˆë‹¤.'),
(6, 1, 91.67, '?„ì²´?ìœ¼ë¡?Java ê¸°ì´ˆ ê°œë…??ë§¤ìš° ???œí˜„?ˆìŠµ?ˆë‹¤. ê°?ë¬¸ì œ???µì‹¬ ?”ì†Œê°€ ?´ë?ì§€????ë°˜ì˜?˜ì—ˆê³? ?„ë¡¬?„íŠ¸ êµ¬ì„±???ˆì •?ì´?ˆìŠµ?ˆë‹¤. ?ìœ¼ë¡œëŠ” ?¸ë? ë°°ì¹˜???¤í??¼ì„ ì¡°ê¸ˆ ??êµ¬ì²´?”í•˜ë©??„ì„±?„ê? ???’ì•„ì§????ˆìŠµ?ˆë‹¤.'),

(4, 3, 78.67, 'Spring MVC?€ DI??ê¸°ë³¸ ?ë¦„?????´í•´?˜ê³  ?ˆìŠµ?ˆë‹¤. ì»¨íŠ¸ë¡¤ëŸ¬?€ ?œë¹„?¤ì˜ ê´€ê³??œí˜„?€ ì¢‹ì•˜ì§€ë§? ?°ì´???´ë™ ?ë¦„????ëª…í™•???‘ì„±?˜ë©´ ??ì¢‹ì? ê²°ê³¼ë¥?ë§Œë“¤ ???ˆìŠµ?ˆë‹¤.'),
(5, 3, 64.33, 'Spring??ì£¼ìš” ê°œë…???¼ë? ë°˜ì˜?ˆì?ë§??„ì²´ êµ¬ì¡° ?œí˜„???¤ì†Œ ë¶€ì¡±í–ˆ?µë‹ˆ?? ?„ë¡¬?„íŠ¸??ê³„ì¸µ êµ¬ì¡°?€ ê°ì²´ ê°?ê´€ê³„ë? ??êµ¬ì²´?ìœ¼ë¡??‘ì„±?˜ëŠ” ?°ìŠµ???„ìš”?©ë‹ˆ??'),
(7, 3, 88.00, 'Spring ?…ë¬¸ ê°œë…???ˆì •?ìœ¼ë¡??œí˜„?ˆìŠµ?ˆë‹¤. ì£¼ìš” êµ¬ì„± ?”ì†Œê°€ ???œëŸ¬?¬ê³ , ?„ì²´?ì¸ ?´ë?ì§€ ë°©í–¥??ì¢‹ì•˜?µë‹ˆ?? ì¡°ê¸ˆ ??êµ¬ì²´?ì¸ êµ¬ë„ ?¤ëª…??ì¶”ê??˜ë©´ ???•í™•??ê²°ê³¼ë¥??»ì„ ???ˆìŠµ?ˆë‹¤.'),

(4, 5, 81.00, 'MySQL??ê¸°ë³¸ ê°œë…ê³??°ì´?°ë² ?´ìŠ¤ êµ¬ì¡°ë¥????œí˜„?ˆìŠµ?ˆë‹¤. ?Œì´ë¸”ê³¼ ?°ì´???€?¥ì˜ ?ë‚Œ?????œëŸ¬?¬ìŠµ?ˆë‹¤. ?¤ë§Œ ê´€ê³„í˜• êµ¬ì¡°ë¥???ëª…í™•???˜í??´ë©´ ?”ìš± ì¢‹ê² ?µë‹ˆ??'),
(6, 5, 59.67, 'MySQLê³?ê´€?¨ëœ ?”ì†Œ???¼ë? ?¬í•¨?˜ì—ˆì§€ë§??µì‹¬ êµ¬ì¡° ?œí˜„??ë¶€ì¡±í–ˆ?µë‹ˆ?? ?Œì´ë¸? ì»¬ëŸ¼, ?°ì´??ê´€ê³„ë? ??ì§ì ‘?ìœ¼ë¡??‘ì„±?˜ëŠ” ê²ƒì´ ?„ìš”?©ë‹ˆ??'),
(8, 5, 94.00, 'MySQL ê¸°ë³¸ êµ¬ì¡°ë¥?ë§¤ìš° ???œí˜„?ˆìŠµ?ˆë‹¤. ?°ì´???€?? ?Œì´ë¸?êµ¬ì„±, ê´€ê³„ì˜ ?ë‚Œ???´ë?ì§€????ë°˜ì˜?˜ì—ˆ?µë‹ˆ?? ?¸ë? ?¤í??¼ë§Œ ì¡°ê¸ˆ ???•ë¦¬?˜ë©´ ?”ìš± ?„ì„±???ˆëŠ” ê²°ê³¼ê°€ ?????ˆìŠµ?ˆë‹¤.'),

(5, 7, 76.33, 'REST API???”ì²­ê³??‘ë‹µ ?ë¦„???„ë°˜?ìœ¼ë¡????œí˜„?ˆìŠµ?ˆë‹¤. ?´ë¼?´ì–¸?¸ì? ?œë²„ ê´€ê³„ê? ?œëŸ¬?¬ì?ë§? HTTP ë©”ì„œ?œë‚˜ ?íƒœ ì½”ë“œ ê°™ì? ?¸ë? ?”ì†Œë¥?ì¶”ê??˜ë©´ ??ì¢‹ìŠµ?ˆë‹¤.'),
(7, 7, 42.00, 'REST API???µì‹¬ ?ë¦„??ì¶©ë¶„???œëŸ¬?˜ì? ?Šì•˜?µë‹ˆ?? ?´ë¼?´ì–¸?? ?œë²„, ?”ì²­, ?‘ë‹µ??ê´€ê³„ë? ?„ë¡¬?„íŠ¸????ëª…í™•???‘ì„±???„ìš”ê°€ ?ˆìŠµ?ˆë‹¤.'),
(8, 7, 89.67, 'REST API???ë¦„ê³?êµ¬ì„± ?”ì†Œë¥?ë§¤ìš° ???œí˜„?ˆìŠµ?ˆë‹¤. ?”ì²­ê³??‘ë‹µ??ë°©í–¥?±ì´ ëª…í™•?ˆê³ , ?„ì²´?ì¸ êµ¬ì¡°???ˆì •?ì´?ˆìŠµ?ˆë‹¤. ?¤ìŒ?ëŠ” ?°ì´???•ì‹ê¹Œì? ??êµ¬ì²´?ìœ¼ë¡??œí˜„?´ë³´ë©?ì¢‹ê² ?µë‹ˆ??'),

(4, 9, 69.33, 'Vue??ê¸°ë³¸ êµ¬ì¡°???´ëŠ ?•ë„ ?œí˜„?˜ì—ˆì§€ë§?ì»´í¬?ŒíŠ¸ ê°?ê´€ê³„ê? ëª…í™•?˜ì? ?Šì•˜?µë‹ˆ?? ?”ë©´ êµ¬ì„±, ì»´í¬?ŒíŠ¸, ?íƒœ ?ë¦„????êµ¬ì²´?ìœ¼ë¡??‘ì„±?˜ë©´ ì¢‹ê² ?µë‹ˆ??'),
(5, 9, 83.00, 'Vue ì»´í¬?ŒíŠ¸?€ ?”ë©´ êµ¬ì„±???ë‚Œ?????´ë ¸?µë‹ˆ?? ì£¼ìš” ?”ì†Œê°€ ?ˆì •?ìœ¼ë¡??œëŸ¬?¬ê³ , ?„ë¡ ?¸ì—”??êµ¬ì¡°ë¥??´í•´?˜ê³  ?ˆë‹¤???ì´ ë³´ì…?ˆë‹¤. ?¼ìš°?°ì? ?íƒœ ?ë¦„ê¹Œì? ?¬í•¨?˜ë©´ ??ì¢‹ì? ê²°ê³¼ê°€ ?????ˆìŠµ?ˆë‹¤.'),
(6, 10, 74.00, '?„ë¡ ?¸ì—”???µì‹  ?ë¦„???´ëŠ ?•ë„ ???œí˜„?ˆìŠµ?ˆë‹¤. Axios?€ ?œë²„ ?µì‹ ??ë°©í–¥?±ì? ë³´ì´ì§€ë§? ?”ì²­ê³??‘ë‹µ ?°ì´?°ì˜ ?ë¦„????êµ¬ì²´?ìœ¼ë¡??‘ì„±?˜ë©´ ì¢‹ê² ?µë‹ˆ??');
*/

-- =========================================================
-- Active development seed data
-- All sample user passwords are: 1234
-- =========================================================

INSERT INTO User
(id, email, password, role, nickname, age, gender, profile)
VALUES
(1, 'teacher1@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'TEACHER', 'Teacher Kim', '1990-03-15', 'M', 'teacher1.png'),
(2, 'teacher2@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'TEACHER', 'Teacher Lee', '1988-07-21', 'F', 'teacher2.png'),
(3, 'teacher3@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'TEACHER', 'Teacher Park', '1992-11-05', 'M', 'teacher3.png'),
(4, 'teacher4@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'TEACHER', 'Teacher Choi', '1986-02-12', 'F', 'teacher4.png'),
(5, 'teacher5@test.com', '{bcrypt}$2a$10$dVCma04hrbMCmzu7VntgO.SK4TQJBb7XqNPX5RcXYDzIDck2EyPvm', 'TEACHER', 'Teacher Jung', '1991-09-28', 'M', 'teacher5.png');

INSERT INTO Quiz
(id, user_id, title, image, level)
VALUES
(1, 1, 'Teacher 1 Image Quiz 1', '/uploads/images/001_student.png', 'EASY'),
(2, 1, 'Teacher 1 Image Quiz 2', '/uploads/images/001_teacher.png', 'EASY'),
(3, 1, 'Teacher 1 Image Quiz 3', '/uploads/images/002_student.png', 'EASY'),
(4, 1, 'Teacher 1 Image Quiz 4', '/uploads/images/002_teacher.png', 'EASY'),
(5, 1, 'Teacher 1 Image Quiz 5', '/uploads/images/003_student.png', 'EASY'),
(6, 1, 'Teacher 1 Image Quiz 6', '/uploads/images/003_teacher.png', 'EASY'),
(7, 1, 'Teacher 1 Image Quiz 7', '/uploads/images/004_student.png', 'EASY'),
(8, 1, 'Teacher 1 Image Quiz 8', '/uploads/images/004_teacher.png', 'NORMAL'),
(9, 1, 'Teacher 1 Image Quiz 9', '/uploads/images/005_student.png', 'NORMAL'),
(10, 1, 'Teacher 1 Image Quiz 10', '/uploads/images/005_teacher.png', 'NORMAL'),
(11, 1, 'Teacher 1 Image Quiz 11', '/uploads/images/006_student.png', 'NORMAL'),
(12, 1, 'Teacher 1 Image Quiz 12', '/uploads/images/006_teacher.png', 'NORMAL'),
(13, 1, 'Teacher 1 Image Quiz 13', '/uploads/images/007_student.png', 'NORMAL'),
(14, 1, 'Teacher 1 Image Quiz 14', '/uploads/images/007_teacher.png', 'NORMAL'),
(15, 1, 'Teacher 1 Image Quiz 15', '/uploads/images/008_student.png', 'HARD'),
(16, 1, 'Teacher 1 Image Quiz 16', '/uploads/images/008_teacher.png', 'HARD'),
(17, 1, 'Teacher 1 Image Quiz 17', '/uploads/images/009_student.png', 'HARD'),
(18, 1, 'Teacher 1 Image Quiz 18', '/uploads/images/009_teacher.png', 'HARD'),
(19, 1, 'Teacher 1 Image Quiz 19', '/uploads/images/010_student.png', 'HARD'),
(20, 1, 'Teacher 1 Image Quiz 20', '/uploads/images/010_teacher.png', 'HARD'),
(21, 2, 'Teacher 2 Image Quiz 1', '/uploads/images/011_student.png', 'EASY'),
(22, 2, 'Teacher 2 Image Quiz 2', '/uploads/images/011_teacher.png', 'EASY'),
(23, 2, 'Teacher 2 Image Quiz 3', '/uploads/images/012_student.png', 'EASY'),
(24, 2, 'Teacher 2 Image Quiz 4', '/uploads/images/012_teacher.png', 'EASY'),
(25, 2, 'Teacher 2 Image Quiz 5', '/uploads/images/013_student.png', 'EASY'),
(26, 2, 'Teacher 2 Image Quiz 6', '/uploads/images/013_teacher.png', 'EASY'),
(27, 2, 'Teacher 2 Image Quiz 7', '/uploads/images/014_student.png', 'EASY'),
(28, 2, 'Teacher 2 Image Quiz 8', '/uploads/images/014_teacher.png', 'NORMAL'),
(29, 2, 'Teacher 2 Image Quiz 9', '/uploads/images/015_student.png', 'NORMAL'),
(30, 2, 'Teacher 2 Image Quiz 10', '/uploads/images/015_teacher.png', 'NORMAL'),
(31, 2, 'Teacher 2 Image Quiz 11', '/uploads/images/016_student.png', 'NORMAL'),
(32, 2, 'Teacher 2 Image Quiz 12', '/uploads/images/016_teacher.png', 'NORMAL'),
(33, 2, 'Teacher 2 Image Quiz 13', '/uploads/images/017_student.png', 'NORMAL'),
(34, 2, 'Teacher 2 Image Quiz 14', '/uploads/images/017_teacher.png', 'NORMAL'),
(35, 2, 'Teacher 2 Image Quiz 15', '/uploads/images/018_student.png', 'HARD'),
(36, 2, 'Teacher 2 Image Quiz 16', '/uploads/images/018_teacher.png', 'HARD'),
(37, 2, 'Teacher 2 Image Quiz 17', '/uploads/images/019_student.png', 'HARD'),
(38, 2, 'Teacher 2 Image Quiz 18', '/uploads/images/019_teacher.png', 'HARD'),
(39, 2, 'Teacher 2 Image Quiz 19', '/uploads/images/020_student.png', 'HARD'),
(40, 2, 'Teacher 2 Image Quiz 20', '/uploads/images/020_teacher.png', 'HARD'),
(41, 3, 'Teacher 3 Image Quiz 1', '/uploads/images/021_student.png', 'EASY'),
(42, 3, 'Teacher 3 Image Quiz 2', '/uploads/images/021_teacher.png', 'EASY'),
(43, 3, 'Teacher 3 Image Quiz 3', '/uploads/images/022_student.png', 'EASY'),
(44, 3, 'Teacher 3 Image Quiz 4', '/uploads/images/022_teacher.png', 'EASY'),
(45, 3, 'Teacher 3 Image Quiz 5', '/uploads/images/023_student.png', 'EASY'),
(46, 3, 'Teacher 3 Image Quiz 6', '/uploads/images/023_teacher.png', 'EASY'),
(47, 3, 'Teacher 3 Image Quiz 7', '/uploads/images/024_student.png', 'EASY'),
(48, 3, 'Teacher 3 Image Quiz 8', '/uploads/images/024_teacher.png', 'NORMAL'),
(49, 3, 'Teacher 3 Image Quiz 9', '/uploads/images/025_student.png', 'NORMAL'),
(50, 3, 'Teacher 3 Image Quiz 10', '/uploads/images/025_teacher.png', 'NORMAL'),
(51, 3, 'Teacher 3 Image Quiz 11', '/uploads/images/026_student.png', 'NORMAL'),
(52, 3, 'Teacher 3 Image Quiz 12', '/uploads/images/026_teacher.png', 'NORMAL'),
(53, 3, 'Teacher 3 Image Quiz 13', '/uploads/images/027_student.png', 'NORMAL'),
(54, 3, 'Teacher 3 Image Quiz 14', '/uploads/images/027_teacher.png', 'NORMAL'),
(55, 3, 'Teacher 3 Image Quiz 15', '/uploads/images/028_student.png', 'HARD'),
(56, 3, 'Teacher 3 Image Quiz 16', '/uploads/images/028_teacher.png', 'HARD'),
(57, 3, 'Teacher 3 Image Quiz 17', '/uploads/images/029_student.png', 'HARD'),
(58, 3, 'Teacher 3 Image Quiz 18', '/uploads/images/029_teacher.png', 'HARD'),
(59, 3, 'Teacher 3 Image Quiz 19', '/uploads/images/030_student.png', 'HARD'),
(60, 3, 'Teacher 3 Image Quiz 20', '/uploads/images/030_teacher.png', 'HARD'),
(61, 4, 'Teacher 4 Image Quiz 1', '/uploads/images/031_student.png', 'EASY'),
(62, 4, 'Teacher 4 Image Quiz 2', '/uploads/images/031_teacher.png', 'EASY'),
(63, 4, 'Teacher 4 Image Quiz 3', '/uploads/images/032_student.png', 'EASY'),
(64, 4, 'Teacher 4 Image Quiz 4', '/uploads/images/032_teacher.png', 'EASY'),
(65, 4, 'Teacher 4 Image Quiz 5', '/uploads/images/033_student.png', 'EASY'),
(66, 4, 'Teacher 4 Image Quiz 6', '/uploads/images/033_teacher.png', 'EASY'),
(67, 4, 'Teacher 4 Image Quiz 7', '/uploads/images/034_student.png', 'EASY'),
(68, 4, 'Teacher 4 Image Quiz 8', '/uploads/images/034_teacher.png', 'NORMAL'),
(69, 4, 'Teacher 4 Image Quiz 9', '/uploads/images/035_student.png', 'NORMAL'),
(70, 4, 'Teacher 4 Image Quiz 10', '/uploads/images/035_teacher.png', 'NORMAL'),
(71, 4, 'Teacher 4 Image Quiz 11', '/uploads/images/036_student.png', 'NORMAL'),
(72, 4, 'Teacher 4 Image Quiz 12', '/uploads/images/036_teacher.png', 'NORMAL'),
(73, 4, 'Teacher 4 Image Quiz 13', '/uploads/images/037_student.png', 'NORMAL'),
(74, 4, 'Teacher 4 Image Quiz 14', '/uploads/images/037_teacher.png', 'NORMAL'),
(75, 4, 'Teacher 4 Image Quiz 15', '/uploads/images/038_student.png', 'HARD'),
(76, 4, 'Teacher 4 Image Quiz 16', '/uploads/images/038_teacher.png', 'HARD'),
(77, 4, 'Teacher 4 Image Quiz 17', '/uploads/images/039_student.png', 'HARD'),
(78, 4, 'Teacher 4 Image Quiz 18', '/uploads/images/039_teacher.png', 'HARD'),
(79, 4, 'Teacher 4 Image Quiz 19', '/uploads/images/040_student.png', 'HARD'),
(80, 4, 'Teacher 4 Image Quiz 20', '/uploads/images/040_teacher.png', 'HARD'),
(81, 5, 'Teacher 5 Image Quiz 1', '/uploads/images/041_student.png', 'EASY'),
(82, 5, 'Teacher 5 Image Quiz 2', '/uploads/images/041_teacher.png', 'EASY'),
(83, 5, 'Teacher 5 Image Quiz 3', '/uploads/images/042_student.png', 'EASY'),
(84, 5, 'Teacher 5 Image Quiz 4', '/uploads/images/042_teacher.png', 'EASY'),
(85, 5, 'Teacher 5 Image Quiz 5', '/uploads/images/043_student.png', 'EASY'),
(86, 5, 'Teacher 5 Image Quiz 6', '/uploads/images/043_teacher.png', 'EASY'),
(87, 5, 'Teacher 5 Image Quiz 7', '/uploads/images/044_student.png', 'EASY'),
(88, 5, 'Teacher 5 Image Quiz 8', '/uploads/images/044_teacher.png', 'NORMAL'),
(89, 5, 'Teacher 5 Image Quiz 9', '/uploads/images/045_student.png', 'NORMAL'),
(90, 5, 'Teacher 5 Image Quiz 10', '/uploads/images/045_teacher.png', 'NORMAL'),
(91, 5, 'Teacher 5 Image Quiz 11', '/uploads/images/046_student.png', 'NORMAL'),
(92, 5, 'Teacher 5 Image Quiz 12', '/uploads/images/046_teacher.png', 'NORMAL'),
(93, 5, 'Teacher 5 Image Quiz 13', '/uploads/images/047_student.png', 'NORMAL'),
(94, 5, 'Teacher 5 Image Quiz 14', '/uploads/images/047_teacher.png', 'NORMAL'),
(95, 5, 'Teacher 5 Image Quiz 15', '/uploads/images/048_student.png', 'HARD'),
(96, 5, 'Teacher 5 Image Quiz 16', '/uploads/images/048_teacher.png', 'HARD'),
(97, 5, 'Teacher 5 Image Quiz 17', '/uploads/images/049_student.png', 'HARD'),
(98, 5, 'Teacher 5 Image Quiz 18', '/uploads/images/049_teacher.png', 'HARD'),
(99, 5, 'Teacher 5 Image Quiz 19', '/uploads/images/050_student.png', 'HARD'),
(100, 5, 'Teacher 5 Image Quiz 20', '/uploads/images/050_teacher.png', 'HARD');

INSERT INTO QuizRoom
(id, user_id, title, room_code, state, level, description, solved_cnt, `like`)
VALUES
(1, 1, 'Teacher 1 Quiz Room 1', 'T01-ROOM-01', 'OPEN', 'EASY', 'Teacher 1 room using quizzes 1 through 4.', 0, 0),
(2, 1, 'Teacher 1 Quiz Room 2', 'T01-ROOM-02', 'OPEN', 'EASY', 'Teacher 1 room using quizzes 5 through 8.', 0, 0),
(3, 1, 'Teacher 1 Quiz Room 3', 'T01-ROOM-03', 'OPEN', 'NORMAL', 'Teacher 1 room using quizzes 9 through 12.', 0, 0),
(4, 1, 'Teacher 1 Quiz Room 4', 'T01-ROOM-04', 'OPEN', 'NORMAL', 'Teacher 1 room using quizzes 13 through 16.', 0, 0),
(5, 1, 'Teacher 1 Quiz Room 5', 'T01-ROOM-05', 'OPEN', 'HARD', 'Teacher 1 room using quizzes 17 through 20.', 0, 0),
(6, 2, 'Teacher 2 Quiz Room 1', 'T02-ROOM-01', 'OPEN', 'EASY', 'Teacher 2 room using quizzes 1 through 4.', 0, 0),
(7, 2, 'Teacher 2 Quiz Room 2', 'T02-ROOM-02', 'OPEN', 'EASY', 'Teacher 2 room using quizzes 5 through 8.', 0, 0),
(8, 2, 'Teacher 2 Quiz Room 3', 'T02-ROOM-03', 'OPEN', 'NORMAL', 'Teacher 2 room using quizzes 9 through 12.', 0, 0),
(9, 2, 'Teacher 2 Quiz Room 4', 'T02-ROOM-04', 'OPEN', 'NORMAL', 'Teacher 2 room using quizzes 13 through 16.', 0, 0),
(10, 2, 'Teacher 2 Quiz Room 5', 'T02-ROOM-05', 'OPEN', 'HARD', 'Teacher 2 room using quizzes 17 through 20.', 0, 0),
(11, 3, 'Teacher 3 Quiz Room 1', 'T03-ROOM-01', 'OPEN', 'EASY', 'Teacher 3 room using quizzes 1 through 4.', 0, 0),
(12, 3, 'Teacher 3 Quiz Room 2', 'T03-ROOM-02', 'OPEN', 'EASY', 'Teacher 3 room using quizzes 5 through 8.', 0, 0),
(13, 3, 'Teacher 3 Quiz Room 3', 'T03-ROOM-03', 'OPEN', 'NORMAL', 'Teacher 3 room using quizzes 9 through 12.', 0, 0),
(14, 3, 'Teacher 3 Quiz Room 4', 'T03-ROOM-04', 'OPEN', 'NORMAL', 'Teacher 3 room using quizzes 13 through 16.', 0, 0),
(15, 3, 'Teacher 3 Quiz Room 5', 'T03-ROOM-05', 'OPEN', 'HARD', 'Teacher 3 room using quizzes 17 through 20.', 0, 0),
(16, 4, 'Teacher 4 Quiz Room 1', 'T04-ROOM-01', 'OPEN', 'EASY', 'Teacher 4 room using quizzes 1 through 4.', 0, 0),
(17, 4, 'Teacher 4 Quiz Room 2', 'T04-ROOM-02', 'OPEN', 'EASY', 'Teacher 4 room using quizzes 5 through 8.', 0, 0),
(18, 4, 'Teacher 4 Quiz Room 3', 'T04-ROOM-03', 'OPEN', 'NORMAL', 'Teacher 4 room using quizzes 9 through 12.', 0, 0),
(19, 4, 'Teacher 4 Quiz Room 4', 'T04-ROOM-04', 'OPEN', 'NORMAL', 'Teacher 4 room using quizzes 13 through 16.', 0, 0),
(20, 4, 'Teacher 4 Quiz Room 5', 'T04-ROOM-05', 'OPEN', 'HARD', 'Teacher 4 room using quizzes 17 through 20.', 0, 0),
(21, 5, 'Teacher 5 Quiz Room 1', 'T05-ROOM-01', 'OPEN', 'EASY', 'Teacher 5 room using quizzes 1 through 4.', 0, 0),
(22, 5, 'Teacher 5 Quiz Room 2', 'T05-ROOM-02', 'OPEN', 'EASY', 'Teacher 5 room using quizzes 5 through 8.', 0, 0),
(23, 5, 'Teacher 5 Quiz Room 3', 'T05-ROOM-03', 'OPEN', 'NORMAL', 'Teacher 5 room using quizzes 9 through 12.', 0, 0),
(24, 5, 'Teacher 5 Quiz Room 4', 'T05-ROOM-04', 'OPEN', 'NORMAL', 'Teacher 5 room using quizzes 13 through 16.', 0, 0),
(25, 5, 'Teacher 5 Quiz Room 5', 'T05-ROOM-05', 'OPEN', 'HARD', 'Teacher 5 room using quizzes 17 through 20.', 0, 0);

INSERT INTO Quiz_QuizRoom
(quiz_id, quiz_room_id, quiz_order)
VALUES
(1, 1, 1), (2, 1, 2), (3, 1, 3), (4, 1, 4),
(5, 2, 1), (6, 2, 2), (7, 2, 3), (8, 2, 4),
(9, 3, 1), (10, 3, 2), (11, 3, 3), (12, 3, 4),
(13, 4, 1), (14, 4, 2), (15, 4, 3), (16, 4, 4),
(17, 5, 1), (18, 5, 2), (19, 5, 3), (20, 5, 4),
(21, 6, 1), (22, 6, 2), (23, 6, 3), (24, 6, 4),
(25, 7, 1), (26, 7, 2), (27, 7, 3), (28, 7, 4),
(29, 8, 1), (30, 8, 2), (31, 8, 3), (32, 8, 4),
(33, 9, 1), (34, 9, 2), (35, 9, 3), (36, 9, 4),
(37, 10, 1), (38, 10, 2), (39, 10, 3), (40, 10, 4),
(41, 11, 1), (42, 11, 2), (43, 11, 3), (44, 11, 4),
(45, 12, 1), (46, 12, 2), (47, 12, 3), (48, 12, 4),
(49, 13, 1), (50, 13, 2), (51, 13, 3), (52, 13, 4),
(53, 14, 1), (54, 14, 2), (55, 14, 3), (56, 14, 4),
(57, 15, 1), (58, 15, 2), (59, 15, 3), (60, 15, 4),
(61, 16, 1), (62, 16, 2), (63, 16, 3), (64, 16, 4),
(65, 17, 1), (66, 17, 2), (67, 17, 3), (68, 17, 4),
(69, 18, 1), (70, 18, 2), (71, 18, 3), (72, 18, 4),
(73, 19, 1), (74, 19, 2), (75, 19, 3), (76, 19, 4),
(77, 20, 1), (78, 20, 2), (79, 20, 3), (80, 20, 4),
(81, 21, 1), (82, 21, 2), (83, 21, 3), (84, 21, 4),
(85, 22, 1), (86, 22, 2), (87, 22, 3), (88, 22, 4),
(89, 23, 1), (90, 23, 2), (91, 23, 3), (92, 23, 4),
(93, 24, 1), (94, 24, 2), (95, 24, 3), (96, 24, 4),
(97, 25, 1), (98, 25, 2), (99, 25, 3), (100, 25, 4);
