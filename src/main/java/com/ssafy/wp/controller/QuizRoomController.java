package com.ssafy.wp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.wp.model.dto.QuizRoom;
import com.ssafy.wp.security.dto.CustomUserDetails;
import com.ssafy.wp.service.QuizRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quizroom")
public class QuizRoomController {
	
	private final QuizRoomService qrService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> select(@PathVariable("id") int id){
		
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
	
	@GetMapping
	public ResponseEntity<?> selectAllByUserId(@AuthenticationPrincipal CustomUserDetails userDetails){
		
		int userId = userDetails.getId();
		
		List<QuizRoom> quizRoomList = qrService.selectAllByUserId(userId);

		return ResponseEntity.ok(Map.of(
				"message", "교사 퀴즈룸 전체 조회 성공",
				"data", quizRoomList
		));
	}
	
	@PostMapping

	public ResponseEntity<?> insert(@AuthenticationPrincipal CustomUserDetails userDetails,

									@RequestBody QuizRoom quizRoom) {
		int userId = userDetails.getId();
		// user_id는 요청 body가 아니라 토큰에서 가져온 교사 id 사용
		quizRoom.setUserId(userId);
		int result = qrService.insert(quizRoom);
		if (result > 0) {
			QuizRoom findQuizRoom = qrService.select(quizRoom.getId());
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
					"message", "퀴즈룸 생성 성공",
					"data", findQuizRoom
			));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
				"message", "퀴즈룸 생성 실패"
		));

	}
}
