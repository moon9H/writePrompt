package com.ssafy.wp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.wp.model.dto.QuizRoom;
import com.ssafy.wp.service.PlayService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Play", description = "퀴즈 진행 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/play")
public class PlayController {
	
	private final PlayService playService;
	
	@Operation(
		summary = "플레이 가능한 퀴즈룸 전체 조회",
		description = "유저가 참여 가능한 OPEN 상태의 퀴즈룸 목록을 조회"
	)
	@GetMapping
	public ResponseEntity<?> selectAll() {
		
		List<QuizRoom> qRooms = playService.selectAll();
		
		return ResponseEntity.ok(Map.of(
				"message", "플레이 가능한 퀴즈룸 전체 조회 성공",
				"data", qRooms
		));
	}
}
