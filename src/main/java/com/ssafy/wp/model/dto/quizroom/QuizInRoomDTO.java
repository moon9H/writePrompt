package com.ssafy.wp.model.dto.quizroom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizInRoomDTO {
	private int quizId;
	private String title;
	private String image;
	private String level;
	private int quizOrder;

}
