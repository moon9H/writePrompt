package com.ssafy.wp.model.dto.quiz;

import java.time.LocalDateTime;

public record QuizResponse(
        int id,
        int userId,
        String title,
        String image,
        String level,
        LocalDateTime createdTime
) {
    public static QuizResponse from(Quiz quiz) {
        return new QuizResponse(
                quiz.getId(),
                quiz.getUserId(),
                quiz.getTitle(),
                quiz.getImage(),
                quiz.getLevel(),
                quiz.getCreatedTime()
        );
    }
}
