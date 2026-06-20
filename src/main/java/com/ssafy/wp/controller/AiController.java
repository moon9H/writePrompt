package com.ssafy.wp.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.wp.common.response.ApiResponse;
import com.ssafy.wp.model.dto.ai.ImageCompareRequest;
import com.ssafy.wp.model.dto.ai.ImageCompareResult;
import com.ssafy.wp.model.dto.ai.ImageGenerateRequest;
import com.ssafy.wp.model.dto.ai.PromptGenerateRequest;
import com.ssafy.wp.service.ai.ImageCompareService;
import com.ssafy.wp.service.ai.ImageGenerationService;
import com.ssafy.wp.service.ai.PromptGenerationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "AI", description = "AI 이미지 생성, 프롬프트 생성, 이미지 비교 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {
	
	private final ImageGenerationService igService;
	private final PromptGenerationService pgService;
	private final ImageCompareService icService;
	
	@Operation(
        summary = "이미지 비교",
        description = "정답 이미지와 사용자가 제출한 이미지를 비교하여 점수와 피드백 생성"
	)
	@PostMapping("/compare")
	public ResponseEntity<?> compareImages(@RequestBody ImageCompareRequest request){
	    if (request == null
	            || isBlank(request.getAnswerImageUrl())
	            || isBlank(request.getStudentImageUrl())) {
	        throw new IllegalArgumentException("잘못된 입력");
	    }

	    ImageCompareResult result = icService.compareImages(request);

	    return ResponseEntity.ok(
	            ApiResponse.ok("이미지 비교 성공", result)
	    );
	}
	
	@Operation(
        summary = "이미지 생성",
        description = "사용자가 입력한 프롬프트를 기반으로 이미지를 생성"
	)
	@PostMapping("/image")
	public ResponseEntity<?> generateImage(@RequestBody ImageGenerateRequest request) throws IOException {
	    
	    if (request == null || isBlank(request.getPrompt())) {
	        throw new IllegalArgumentException("잘못된 입력");
	    }

	    String image = igService.generateImage(request.getPrompt());

	    return ResponseEntity.ok(
	            ApiResponse.ok("이미지 생성 성공", image)
	    );
	}
	
	@Operation(
        summary = "프롬프트 생성",
        description = "퀴즈 생성에 필요한 조건을 바탕으로 이미지 생성용 프롬프트를 생성"
	)
	@PostMapping("/prompt")
	public ResponseEntity<?> generatePrompt(@RequestBody PromptGenerateRequest request){
	    
	    if (isInvalidPromptRequest(request)) {
	        throw new IllegalArgumentException("잘못된 입력");
	    }

	    String prompt = pgService.generatePrompt(request);

	    return ResponseEntity.ok(
	            ApiResponse.ok("프롬프트 생성 성공", prompt)
	    );
	}
	
	private boolean isInvalidPromptRequest(PromptGenerateRequest request) {
        return request == null
                || isBlank(request.getLevel())
                || isBlank(request.getBackground())
                || isBlank(request.getTheme())
                || isBlank(request.getEmotion())
                || isBlank(request.getCharacter())
                || isBlank(request.getArtMedia())
                || isBlank(request.getColor())
                || isBlank(request.getCameraShot());
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
