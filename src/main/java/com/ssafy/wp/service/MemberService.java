package com.ssafy.wp.service;

import com.ssafy.wp.model.dto.member.Member;
import com.ssafy.wp.model.dto.member.MemberDetailResponse;
import com.ssafy.wp.model.dto.member.MemberRequest;

public interface MemberService {
	// id 기반 멤버 조회
	Member select(int id);

	// 회원 정보와 풀이 결과 목록 조회
	MemberDetailResponse selectDetail(int id);

	// 새로운 회원 가입
	Member insert(MemberRequest member);
	
	// 회원 정보 수정
	Member update(int memberId, MemberRequest request);
	
	// 회원 삭제
	int delete(int id);
}
