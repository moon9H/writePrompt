package com.ssafy.wp.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.wp.model.dto.QuizInRoomResponse;
import com.ssafy.wp.model.dto.QuizRoom;
import com.ssafy.wp.model.dto.QuizRoomResponse;

@Mapper
public interface QuizRoomDao {
	
	// 특정 퀴즈룸 조회
	QuizRoomResponse select(int id);
	
	// 특정 퀴즈룸에 포함된 퀴즈 목록 조회
	List<QuizInRoomResponse> selectQuizListByRoomId(int quizRoomId);
	
	// 특정 교사가 생성한 퀴즈룸 전체 조회
	List<QuizRoom> selectAllByUserId(int userId);
	
	// 퀴즈룸 생성
	int insert(QuizRoom quizRoom);
}
