package com.ssafy.wp.model.dto;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class PlayAnswerResponse {
	
	private double avgScore;
	private String finalFeedback;
}
