package com.ssafy.wp.model.dto.play;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// 각 문제에 대한 필요한 결과 저장 DTO
public class PlayAnswerItem {
	private int quizOrder;
	private int quizId;
	private int score;
	private String feedback;
}
