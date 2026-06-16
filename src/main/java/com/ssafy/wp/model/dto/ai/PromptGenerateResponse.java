package com.ssafy.wp.model.dto.ai;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromptGenerateResponse {
	
	@Schema(description = "응답 메시지", example = "프롬프트 생성 성공")
    private String message;

    @Schema(description = "이미지 생성을 위한 프롬프트", example = "미래 도시 배경에서 흥미롭게 놀고 있는 학생 개발자의 모습을 담은 디지털 일러스트. 주로 파란색과 흰색 톤으로 구성된 와이드샷.")
    private String data;
}
