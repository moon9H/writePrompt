package com.ssafy.wp.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.wp.model.dto.member.Member;
import com.ssafy.wp.model.dto.play.QuizResult;

@Mapper
public interface MemberDao {
	
	// id 기반 멤버 조회
	Member select(int id);
	
	// 새로운 회원 가입
	int insert(Member member);
	
	// 회원 정보 수정
	int update(Member member);
	
	// 회원 삭제
	int delete(int id);

	Member selectByEmail(String email);

	List<QuizResult> selectQuizResultsByUserId(int userId);
}
