package com.ssafy.wp.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizRoomResponse {
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

	private List<QuizInRoomResponse> quizList;
}