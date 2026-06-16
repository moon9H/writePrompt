package com.ssafy.wp.model.dto.ai;

import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class ImageCompareRequest {
	@Schema(description = "교사 정답 이미지 URL", example = "/uploads/images/answer.png")
    private String answerImageUrl;

    @Schema(description = "학생 생성 이미지 URL", example = "/uploads/images/student.png")
    private String studentImageUrl;
}
