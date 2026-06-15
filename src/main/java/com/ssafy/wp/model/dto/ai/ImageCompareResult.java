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
public class ImageCompareResult {
	@Schema(description = "유사도 점수", example = "85")
    int score;

    @Schema(description = "비교 피드백", example = "핵심 주제와 색감은 유사하지만 구도 표현이 일부 다릅니다.")
    String feedback;
}
