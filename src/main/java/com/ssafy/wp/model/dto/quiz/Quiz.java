package com.ssafy.wp.model.dto.quiz;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
	private int id;
	private int userId;
	private String title;
	private String image;
	private String level;
	private LocalDateTime createdTime;
}
