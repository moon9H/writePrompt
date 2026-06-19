package com.ssafy.wp.service;

import com.ssafy.wp.model.dto.member.Member;

public interface MemberService {
	// id 기반 멤버 조회
	Member select(int id);
	// 새로운 회원 가입
	int insert(Member member);
	
	// 회원 정보 수정
	int update(Member member);
	
	// 회원 삭제
	int delete(int id);
}
