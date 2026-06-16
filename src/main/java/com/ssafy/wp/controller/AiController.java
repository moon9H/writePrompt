package com.ssafy.wp.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.wp.model.dto.ai.ImageCompareRequest;
import com.ssafy.wp.model.dto.ai.ImageCompareResponse;
import com.ssafy.wp.model.dto.ai.ImageCompareResult;
import com.ssafy.wp.model.dto.ai.ImageGenerateRequest;
import com.ssafy.wp.model.dto.ai.ImageGenerateResponse;
import com.ssafy.wp.model.dto.ai.PromptGenerateRequest;
import com.ssafy.wp.model.dto.ai.PromptGenerateResponse;
import com.ssafy.wp.service.ImageCompareService;
import com.ssafy.wp.service.ImageGenerationService;
import com.ssafy.wp.service.PromptGenerationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {
	
	private final ImageGenerationService igService;
	private final PromptGenerationService pgService;
	private final ImageCompareService icService;
	
	@PostMapping("/compare")
	public ResponseEntity<ImageCompareResponse> compareImages(
			@RequestBody ImageCompareRequest request){
		if (request == null
				|| isBlank(request.getAnswerImageUrl())
				|| isBlank(request.getStudentImageUrl())) {
			return ResponseEntity.badRequest()
	                .body(new ImageCompareResponse("잘못된 입력", null));
		}
		
		try {
			ImageCompareResult result = icService.compareImages(request);
			
			return ResponseEntity.ok(
	                new ImageCompareResponse("이미지 비교 성공", result)
	        );
		} catch (IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
	                .body(new ImageCompareResponse("이미지 비교 실패", null));
		} catch (IllegalArgumentException e) {
		    return ResponseEntity.badRequest()
		            .body(new ImageCompareResponse("잘못된 입력", null));

		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
		            .body(new ImageCompareResponse("이미지 비교 실패", null));
		}
	}
	
	@PostMapping("/image")
	public ResponseEntity<ImageGenerateResponse> generateImage(
	        @RequestBody ImageGenerateRequest request) {
	    
		if (request == null || isBlank(request.getPrompt())) {
	        return ResponseEntity.badRequest()
	                .body(new ImageGenerateResponse("잘못된 입력", null));
	    }

	    try {
	        String image = igService.generateImage(request.getPrompt());
	        return ResponseEntity.ok(
	                new ImageGenerateResponse("이미지 생성 성공", image)
	        );
	    } catch (IllegalStateException e) {
	        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
	                .body(new ImageGenerateResponse("이미지 생성 실패", null));

	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ImageGenerateResponse("이미지 저장 실패", null));
	    }
	}
	
	@PostMapping("/prompt")
	public ResponseEntity<PromptGenerateResponse> generatePrompt(
				@RequestBody PromptGenerateRequest request){
		
		if (isInvalidPromptRequest(request)) {
            return ResponseEntity.badRequest()
                    .body(new PromptGenerateResponse("잘못된 입력", null));
        }
		
		try {
			String prompt = pgService.generatePrompt(request);
			return ResponseEntity.ok(
                    new PromptGenerateResponse("프롬프트 생성 성공", prompt)

            );
		} catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(new PromptGenerateResponse("프롬프트 생성 실패", null));
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
