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

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {
	
	private final ImageGenerationService igService;
	private final PromptGenerationService pgService;
	private final ImageCompareService icService;
	
	@PostMapping("/compare")
	public ResponseEntity<?> compareImages(
			@RequestBody ImageCompareRequest request){
		if (request == null
				|| isBlank(request.getAnswerImageUrl())
				|| isBlank(request.getStudentImageUrl())) {
			return ResponseEntity.badRequest().body(
			        ApiResponse.fail("잘못된 입력")
			);
		}
		
		try {
			ImageCompareResult result = icService.compareImages(request);
			
			return ResponseEntity.ok(
			        ApiResponse.ok("이미지 비교 성공", result)
			);
		} catch (IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
			        ApiResponse.fail("이미지 비교 실패")
			);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(
			        ApiResponse.fail("잘못된 입력")
			);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
			        ApiResponse.fail("이미지 비교 실패")
			);
		}
	}
	
	@PostMapping("/image")
	public ResponseEntity<?> generateImage(
	        @RequestBody ImageGenerateRequest request) {
	    
		if (request == null || isBlank(request.getPrompt())) {
			return ResponseEntity.badRequest().body(
			        ApiResponse.fail("잘못된 입력")
			);
	    }

	    try {
	        String image = igService.generateImage(request.getPrompt());
	        
	        return ResponseEntity.ok(
	                ApiResponse.ok("이미지 생성 성공", image)
	        );
	    } catch (IllegalStateException e) {
	    	return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
	    	        ApiResponse.fail("이미지 생성 실패")
	    	);

	    } catch (IOException e) {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
	    	        ApiResponse.fail("이미지 저장 실패")
	    	);
	    }
	}
	
	@PostMapping("/prompt")
	public ResponseEntity<?> generatePrompt(
				@RequestBody PromptGenerateRequest request){
		
		if (isInvalidPromptRequest(request)) {
			return ResponseEntity.badRequest().body(
			        ApiResponse.fail("잘못된 입력")
			);
        }
		
		try {
			String prompt = pgService.generatePrompt(request);
			
			return ResponseEntity.ok(
			        ApiResponse.ok("프롬프트 생성 성공", prompt)
			);
		} catch (IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
			        ApiResponse.fail("프롬프트 생성 실패")
			);
        }
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
