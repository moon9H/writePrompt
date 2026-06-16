package com.ssafy.wp.service;

import com.ssafy.wp.model.dto.QuizRoom;

public interface QuizRoomService {
	
	 // 특정 퀴즈룸 조회
	QuizRoom select(Long id);
}
