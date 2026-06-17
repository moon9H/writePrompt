package com.ssafy.wp.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizRoomCreateRequest {
	private String title;
	private String level;
	private String description;
    private String state;
	private List<QuizInRoomDTO> quizList;
}
