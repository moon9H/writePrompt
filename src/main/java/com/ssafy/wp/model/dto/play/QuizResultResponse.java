package com.ssafy.wp.model.dto.play;

import java.time.LocalDate;

public record QuizResultResponse(
        int id,
        int quizRoomId,
        double score,
        String feedback,
        LocalDate createdTime
) {
    public static QuizResultResponse from(QuizResult result) {
        return new QuizResultResponse(
                result.getId(),
                result.getQuizRoomId(),
                result.getScore(),
                result.getFeedback(),
                result.getCreatedTime()
        );
    }
}