package com.ssafy.wp.model.dto.member;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MemberResponse(
        int id,
        String email,
        String role,
        String nickname,
        LocalDate age,
        String gender,
        String profile,
        LocalDateTime createdTime) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getEmail(),
                member.getRole(),
                member.getNickname(),
                member.getAge(),
                member.getGender(),
                member.getProfile(),
                member.getCreatedTime()
        );
    }
}