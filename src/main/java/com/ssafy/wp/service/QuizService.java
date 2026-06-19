package com.ssafy.wp.service;

import java.util.List;

import com.ssafy.wp.model.dto.quiz.Quiz;

public interface QuizService {
	
	// 새로운 퀴즈 생성
	int insert(Quiz quiz);
	
	// 퀴즈 삭제
	int delete(int id);
	
	// 퀴즈 제목 수정
	int update(int id, String title);
	
	// 특정 교사가 생성한 퀴즈 전체 조회
	List<Quiz> selectAll(int userId);
	
	// 퀴즈 id 기반으로 특정 퀴즈 조회
	Quiz select(int id);
}
