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
public class ImageCompareResponse {
	@Schema(description = "응답 메시지", example = "이미지 비교 성공")
    private String message;

    @Schema(description = "이미지 비교 결과")
    private ImageCompareResult data;
}
