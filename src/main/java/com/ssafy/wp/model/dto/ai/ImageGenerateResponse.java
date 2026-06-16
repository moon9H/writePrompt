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
public class ImageGenerateResponse {
	
    @Schema(description = "응답 메시지", example = "이미지 생성 성공")
    private String message;
    
    @Schema(description = "생성된 이미지 URL", example = "/uploads/images/***.png")
    private String data;
}
