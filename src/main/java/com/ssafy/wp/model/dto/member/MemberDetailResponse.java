package com.ssafy.wp.model.dto.member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.ssafy.wp.model.dto.play.QuizResultResponse;

public record MemberDetailResponse(
        int id,
        String email,
        String role,
        String nickname,
        LocalDate age,
        String gender,
        String profile,
        LocalDateTime createdTime,
        double averageScore,
        int solvedCount,
        double highestScore,
        List<QuizResultResponse> quizResults
) {
    public static MemberDetailResponse from(
            Member member,
            List<QuizResultResponse> quizResults,
            double averageScore,
            int solvedCount,
            double highestScore
    ) {
        return new MemberDetailResponse(
                member.getId(),
                member.getEmail(),
                member.getRole(),
                member.getNickname(),
                member.getAge(),
                member.getGender(),
                member.getProfile(),
                member.getCreatedTime(),
                averageScore,
                solvedCount,
                highestScore,
                quizResults
        );
    }
}
