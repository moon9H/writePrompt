package com.ssafy.wp.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.wp.model.dto.play.QuizResult;
import com.ssafy.wp.model.dto.quizroom.QuizInRoomDTO;
import com.ssafy.wp.model.dto.quizroom.QuizRoom;
import com.ssafy.wp.model.dto.quizroom.QuizRoomDetailResponse;

@Mapper
public interface PlayDao {
	
	// 유저가 플레이할 수 있는 퀴즈룸 전체 조회
	List<QuizRoom> selectAll();
	
	// 제목으로 플레이 가능한 퀴즈룸 검색
	List<QuizRoom> searchByTitle(String title);
	
	// 퀴즈룹 좋아요 기능
	int increaseLike(int quizRoomId);
	
	// 퀴즈룸 입장을 위한 상세 조회
	QuizRoomDetailResponse selectDetail(int quizRoomId);
	
	// 퀴즈룸 내에 포함되어져 있는 퀴즈 목록 조회 (위의 메서드만으로는 퀴즈 목록까지 한번에 받아올 수 없어서 해당 메서드까지 사용)
	List<QuizInRoomDTO> selectQuizList(int quizRoomId);
	
	// play 결과 저장
	int insertResult(QuizResult result);
	
	// 퀴즈룸 풀이 수 증가
	int increaseSolvedCnt(int quizRoomId);
}