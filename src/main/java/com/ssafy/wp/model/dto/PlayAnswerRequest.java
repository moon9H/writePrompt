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
// 최종 피드백을 받기 위한 DTO
public class PlayAnswerRequest {
	private List<PlayAnswerItem> results;
}
