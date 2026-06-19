package com.ssafy.wp.model.dto.quizroom;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// DB 테이블용 DTO
public class QuizRoom {
	private int id;
	private int userId;
	private String title;
	private LocalDateTime createdTime;
	private String roomCode;
	private String state;
	private String level;
	private String description;
	private int solvedCnt;
	private int like;
}
