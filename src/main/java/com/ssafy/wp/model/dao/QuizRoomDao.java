package com.ssafy.wp.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.wp.model.dto.QuizRoom;

@Mapper
public interface QuizRoomDao {
	
	// 특정 퀴즈룸 조회
	QuizRoom select(Long id);
}
