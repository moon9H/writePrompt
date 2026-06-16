package com.ssafy.wp.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizInRoomResponse {
	private int quizId;
	private String title;
	private String image;
	private String level;
	private int quizOrder;
}