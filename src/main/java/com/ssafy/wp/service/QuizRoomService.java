package com.ssafy.wp.service;

import java.util.List;

import com.ssafy.wp.model.dto.QuizRoom;

public interface QuizRoomService {
	
	 // 특정 퀴즈룸 조회
	QuizRoom select(int id);
	
	// 특정 교사가 생성한 퀴즈룸 전체 조회
	List<QuizRoom> selectAllByUserId(int userId);
}
