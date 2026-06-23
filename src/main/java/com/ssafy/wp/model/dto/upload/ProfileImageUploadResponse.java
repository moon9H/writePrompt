package com.ssafy.wp.model.dto.upload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileImageUploadResponse {

    @Schema(description = "브라우저에서 접근 가능한 프로필 이미지 URL", example = "/uploads/profiles/abc123.png")
    private String imageUrl;
}
