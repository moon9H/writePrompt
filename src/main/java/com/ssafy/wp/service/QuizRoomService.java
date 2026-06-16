package com.ssafy.wp.service;

import java.util.List;

import com.ssafy.wp.model.dto.QuizRoom;
import com.ssafy.wp.model.dto.QuizRoomCreateRequest;
import com.ssafy.wp.model.dto.QuizRoomDetailResponse;

public interface QuizRoomService {
	
	 // 특정 퀴즈룸 조회
	QuizRoomDetailResponse select(int id);
	
	// 특정 교사가 생성한 퀴즈룸 전체 조회
	List<QuizRoom> selectAllByUserId(int userId);
	
	// 퀴즈룸 생성
	int insert(int userId, QuizRoomCreateRequest request);
	
	// 퀴즈룸 수정
	int update(int id, int userId, QuizRoomCreateRequest request);
}
