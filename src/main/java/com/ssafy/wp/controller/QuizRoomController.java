package com.ssafy.wp.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.wp.model.dto.QuizRoom;
import com.ssafy.wp.service.QuizRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quizroom")
public class QuizRoomController {
	
	private final QuizRoomService qrService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> select(@PathVariable("id") Long id){
		
		QuizRoom quizRoom = qrService.select(id);
		
		if (quizRoom != null) {
			return ResponseEntity.ok(Map.of(
						"message","퀴즈룸 조회 성공",
						"data", quizRoom)
					);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).
					body(Map.of("message", "해당 퀴즈룸 찾을 수 없음"));
		}
	}
}
