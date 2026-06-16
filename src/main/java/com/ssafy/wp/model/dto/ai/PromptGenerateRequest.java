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
public class PromptGenerateRequest {
	
	@Schema(description = "퀴즈 난이도", example = "초급")
    private String level;

    @Schema(description = "이미지 배경", example = "미래 도시")
    private String background;

    @Schema(description = "이미지 테마(어떤걸 표현할지? 핵심 주제)", example = "재밌게 놀고 있는 모습")
    private String theme;

    @Schema(description = "이미지 분위기 또는 감정", example = "흥미로운")
    private String emotion;

    @Schema(description = "등장 캐릭터", example = "학생 개발자")
    private String character;

    @Schema(description = "그림 매체 또는 스타일", example = "디지털 일러스트")
    private String artMedia;

    @Schema(description = "주요 색감", example = "파란색과 흰색")
    private String color;

    @Schema(description = "카메라 구도", example = "와이드샷")
    private String cameraShot;
}
