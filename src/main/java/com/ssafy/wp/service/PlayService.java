package com.ssafy.wp.service;

import java.util.List;

import com.ssafy.wp.model.dto.play.PlayAnswerRequest;
import com.ssafy.wp.model.dto.play.PlayAnswerResponse;
import com.ssafy.wp.model.dto.quizroom.QuizRoom;
import com.ssafy.wp.model.dto.quizroom.QuizRoomDetailResponse;

public interface PlayService {
	
	// 유저가 플레이할 수 있는 퀴즈룸 전체 조회
	List<QuizRoom> selectAll();
	
	// 제목으로 플레이 가능한 퀴즈룸 검색
	List<QuizRoom> searchByTitle(String title);
	
	// 퀴즈룹 좋아요 기능
	int increaseLike(int quizRoomId);
	
	// 퀴즈룸 입장을 위한 상세 조회
	QuizRoomDetailResponse selectDetail(int quizRoomId);
	
	// 최종 제출 처리: 평균 계산, AI 총평 생성, 결과 저장, solved_cnt 증가
	PlayAnswerResponse submitFinalAnswer(int userId, int quizRoomId, PlayAnswerRequest request);
}
