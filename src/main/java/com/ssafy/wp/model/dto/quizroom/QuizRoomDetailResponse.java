package com.ssafy.wp.model.dto.quizroom;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizRoomDetailResponse {
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

	private List<QuizInRoomDTO> quizList;
}