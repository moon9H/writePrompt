package com.ssafy.wp.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.wp.model.dto.QuizRoom;

@Mapper
public interface PlayDao {
	
	// 유저가 플레이할 수 있는 퀴즈룸 전체 조회
	List<QuizRoom> selectAll();
	
	// 제목으로 플레이 가능한 퀴즈룸 검색
	List<QuizRoom> searchByTitle(String title);
}