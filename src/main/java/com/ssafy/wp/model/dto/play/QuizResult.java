package com.ssafy.wp.model.dto.play;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// QuizResult 테이블에 저장할 DTO
public class QuizResult {
	private int id;
	private int userId;
	private int quizRoomId;
	
	private double score;
	private String feedback;
	
	private LocalDate createdTime;
}
