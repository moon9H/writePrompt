package com.ssafy.wp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.wp.model.dao.MemberDao;
import com.ssafy.wp.model.dto.member.Member;

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
	public int insert(Member member) {
		String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);

        return mDao.insert(member);
	}

	@Override
	public int update(Member member) {
		return mDao.update(member);
	}

	@Override
	public int delete(int id) {
		return mDao.delete(id);
	}

}
