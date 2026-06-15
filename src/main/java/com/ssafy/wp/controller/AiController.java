package com.ssafy.wp.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.wp.model.dto.ai.ImageGenerateRequest;
import com.ssafy.wp.model.dto.ai.ImageGenerateResponse;
import com.ssafy.wp.service.ImageGenerationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {
	
	private final ImageGenerationService igService;
	
	@PostMapping("/image")

	public ResponseEntity<ImageGenerateResponse> generateImage(
	        @RequestBody ImageGenerateRequest request) {
	    String userPrompt = request.getPrompt();
	    if (userPrompt == null || userPrompt.isBlank()) {
	        return ResponseEntity.badRequest()
	                .body(new ImageGenerateResponse("잘못된 입력", null));
	    }

	    try {
	        String image = igService.generateImage(userPrompt);
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
}
