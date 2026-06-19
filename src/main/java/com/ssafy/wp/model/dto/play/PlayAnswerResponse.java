package com.ssafy.wp.model.dto.play;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayAnswerResponse {
	
	private double avgScore;
	private String finalFeedback;
}
