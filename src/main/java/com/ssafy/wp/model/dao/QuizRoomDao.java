package com.ssafy.wp.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.wp.model.dto.QuizRoom;

@Mapper
public interface QuizRoomDao {
	
	// 특정 퀴즈룸 조회
	QuizRoom select(int id);
	
	// 특정 교사가 생성한 퀴즈룸 전체 조회
	List<QuizRoom> selectAllByUserId(int userId);
}
