package com.ssafy.wp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.wp.model.dao.MemberDao;
import com.ssafy.wp.model.dto.member.Member;
import com.ssafy.wp.model.dto.member.MemberRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	@Autowired
	private final MemberDao mDao;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Member select(int id) {
		return mDao.select(id);
	}

	@Override
	public Member insert(MemberRequest request) {
		Member member = new Member();
		
	    member.setEmail(request.getEmail());
	    member.setPassword(passwordEncoder.encode(request.getPassword()));
	    member.setRole(normalizeRole(request.getRole()));
	    member.setNickname(request.getNickname());
	    member.setAge(request.getAge());
	    member.setGender(request.getGender());
	    member.setProfile(request.getProfile());

	    int result = mDao.insert(member);

	    if (result <= 0) {
	        return null;
	    }

	    return mDao.select(member.getId());
	}

	@Override
	public Member update(int memberId, MemberRequest request) {
	    Member member = new Member();

	    member.setId(memberId);
	    member.setNickname(request.getNickname());
	    member.setAge(request.getAge());
	    member.setGender(request.getGender());
	    member.setProfile(request.getProfile());

	    int result = mDao.update(member);

	    if (result <= 0) {
	        return null;
	    }

	    return mDao.select(memberId);
	}

	@Override
	public int delete(int id) {
		return mDao.delete(id);
	}

	private String normalizeRole(String role) {
		if (role == null || role.isBlank()) {
			throw new IllegalArgumentException("권한을 입력해야 합니다.");
		}

		String normalizedRole = role.toUpperCase();

		if (!normalizedRole.equals("STUDENT") && !normalizedRole.equals("TEACHER")) {
			throw new IllegalArgumentException("잘못된 권한입니다.");
		}

		return normalizedRole;
	}
}
