package com.ssafy.wp.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.wp.model.dao.MemberDao;
import com.ssafy.wp.model.dto.Member;
import com.ssafy.wp.security.dto.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Member member = memberDao.selectByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException("존재하지 않는 회원입니다.");
        }

        return new CustomUserDetails(member);
    }
    
    
 // JWT 검증 필터에서 사용
    public CustomUserDetails loadUserById(int memberId) {
        Member member = memberDao.select(memberId);

        if (member == null) {
            throw new UsernameNotFoundException("존재하지 않는 회원입니다.");
        }

        return new CustomUserDetails(member);
    }
}
