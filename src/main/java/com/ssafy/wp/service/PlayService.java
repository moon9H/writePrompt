package com.ssafy.wp.service;

import java.util.List;

import com.ssafy.wp.model.dto.QuizRoom;

public interface PlayService {
	
	// 유저가 플레이할 수 있는 퀴즈룸 전체 조회
	List<QuizRoom> selectAll();
}
