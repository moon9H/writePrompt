package com.ssafy.wp.model.dto.ai;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageGenerateRequest {
    @Schema(description = "이미지 생성을 위한 사용자 프롬프트", example = "아이가 놀고 있는 사진 그려줘.")
	private String prompt;
}
