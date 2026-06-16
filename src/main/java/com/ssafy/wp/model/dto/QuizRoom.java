package com.ssafy.wp.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizRoom {
	private Long id;
	private Long userId;
	private String title;
	private LocalDateTime createdTime;
	private String roomCode;
	private String state;
	private String level;
	private String description;
	private Long solvedCnt;
	private Long like;
}
