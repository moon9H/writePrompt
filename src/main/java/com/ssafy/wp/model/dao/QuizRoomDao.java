package com.ssafy.wp.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.wp.model.dto.QuizInRoomDTO;
import com.ssafy.wp.model.dto.QuizRoom;
import com.ssafy.wp.model.dto.QuizRoomDetailResponse;

@Mapper
public interface QuizRoomDao {
	
	// 특정 퀴즈룸 조회
	QuizRoomDetailResponse select(int id);
	
	// 특정 퀴즈룸에 포함된 퀴즈 목록 조회
	List<QuizInRoomDTO> selectQuizListByRoomId(int quizRoomId);
	
	// 특정 교사가 생성한 퀴즈룸 전체 조회
	List<QuizRoom> selectAllByUserId(int userId);
	
	// 퀴즈룸 생성
	int insert(QuizRoom quizRoom);
	
	// 퀴즈룸에 퀴즈 연결 메서드 (Quiz_QuizRoom table에 삽입)
	int insertQuizIntoQuizRoom(@Param("quizRoomId") int quizRoomId,
								@Param("quizId") int quizId,
								@Param("quizOrder") int quizOrder);
	
	// 퀴즈룸 수정
	int update(QuizRoom quizRoom);
	
	// 퀴즈룸에 연결되어 있던 기존 퀴즈 목록 삭제 (퀴즈룹 수정 시, Quiz_QuizRoom table에 있던 것 다 지우고 다시 삽입)
	int deleteQuizListByRoomId(@Param("quizRoomId") int quizRoomId,
								@Param("userId") int userId);

	//퀴즈룸 삭제
	int delete(@Param("id") int id,
				@Param("userId") int userId);
}
