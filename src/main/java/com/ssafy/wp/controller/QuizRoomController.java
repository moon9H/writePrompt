package com.ssafy.wp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.wp.model.dto.QuizRoom;
import com.ssafy.wp.model.dto.QuizRoomCreateRequest;
import com.ssafy.wp.model.dto.QuizRoomDetailResponse;
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
		
		QuizRoomDetailResponse quizRoom = qrService.select(id);
		
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
		
		// 해당 교사가 생성한 퀴즈룸 리스트만 쭉 받아옴
		// 그래서 그 목록 중 어떤 퀴즈룸을 클릭하면 위의 select로 퀴즈룸 내의 퀴즈까지 쭉 조회해보는 형식으로 구성
		List<QuizRoom> quizRoomList = qrService.selectAllByUserId(userId);

		return ResponseEntity.ok(Map.of(
				"message", "교사 퀴즈룸 전체 조회 성공",
				"data", quizRoomList
		));
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@AuthenticationPrincipal CustomUserDetails userDetails,
									@RequestBody QuizRoomCreateRequest request) {

		if (request.getQuizList() == null || request.getQuizList().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
					"message", "퀴즈를 최소 1개 이상 선택해야 합니다."
			));
		}

		int userId = userDetails.getId();
		int result = qrService.insert(userId, request);
		
		if (result > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
					"message", "퀴즈룸 생성 성공"
			));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
				"message", "잘못된 입력"
		));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> update(@AuthenticationPrincipal CustomUserDetails userDetails,
									@PathVariable("id") int id,
									@RequestBody QuizRoomCreateRequest request){
		if (request.getTitle() == null || request.getTitle().isBlank()) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
					"message", "제목을 입력해야 합니다."
			));
		}
		if (request.getLevel() == null || request.getLevel().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
					"message", "난이도를 입력해야 합니다."
			));
		}
		if (request.getDescription() == null || request.getDescription().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
					"message", "설명을 입력해야 합니다."
			));
		}
		if (request.getQuizList() == null || request.getQuizList().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
					"message", "퀴즈를 최소 1개 이상 선택해야 합니다."
			));
		}
		int userId = userDetails.getId();
		int result = qrService.update(id, userId, request);
		
		if (result > 0) {
			return ResponseEntity.ok(Map.of(
					"message", "퀴즈룸 수정 성공"
			));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
				"message", "찾을 수 없음"
		));
	}
}
