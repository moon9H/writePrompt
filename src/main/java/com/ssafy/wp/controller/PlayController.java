package com.ssafy.wp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.wp.common.response.ApiResponse;
import com.ssafy.wp.model.dto.play.PlayAnswerRequest;
import com.ssafy.wp.model.dto.play.PlayAnswerResponse;
import com.ssafy.wp.model.dto.quizroom.QuizRoom;
import com.ssafy.wp.model.dto.quizroom.QuizRoomDetailResponse;
import com.ssafy.wp.security.dto.CustomUserDetails;
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
		
		return ResponseEntity.ok(
		        ApiResponse.ok("플레이 가능한 퀴즈룸 전체 조회 성공", qRooms)
		);
	}
	
	@Operation(
		summary = "플레이 가능한 퀴즈룸 제목 검색",
		description = "유저가 참여 가능한 OPEN 상태의 퀴즈룸을 제목으로 검색"
	)
	@GetMapping("/search")
	public ResponseEntity<?> searchByTitle(@RequestParam String title) {

		List<QuizRoom> qRooms = playService.searchByTitle(title);
		
		return ResponseEntity.ok(
		        ApiResponse.ok("플레이 가능한 퀴즈룸 검색 성공", qRooms)
		);

	}
	
	@Operation(
		summary = "퀴즈룸 좋아요 등록",
		description = "퀴즈룸 좋아요 수 +1"
	)
	@PostMapping("/like/{quizroomid}")
	public ResponseEntity<?> increaseLike(@PathVariable("quizroomid") int quizRoomId){
		
		int result = playService.increaseLike(quizRoomId);
		
		if (result > 0) {
		    return ResponseEntity.ok(
		            ApiResponse.ok("좋아요 성공")
		    );

		} else {
		    return ResponseEntity.badRequest().body(
		            ApiResponse.fail("잘못된 요청")
		    );
		}
	}
	
	@Operation(
		summary = "퀴즈룸 입장",
		description = "유저가 OPEN 상태의 퀴즈룸에 입장하고 퀴즈 목록을 조회"
	)
	@GetMapping("/{quizroomid}")
	public ResponseEntity<?> selectDetail(@PathVariable("quizroomid") int quizRoomId) {
		
		QuizRoomDetailResponse quizRoom = playService.selectDetail(quizRoomId);
		
		if (quizRoom == null) {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
		            ApiResponse.fail("존재하지 않는 퀴즈룸")
		    );
		} else {
		    return ResponseEntity.ok(
		            ApiResponse.ok("퀴즈룸 입장 성공", quizRoom)
		    );
		}
	}
	
	@Operation(
		summary = "최종 제출",
		description = "문제별 결과를 바탕으로 평균 점수와 AI 최종 피드백을 생성하고 결과를 저장"
	)
	@PostMapping("/answer/{quizroomid}")
	public ResponseEntity<?> submitFinalAnswer(@PathVariable("quizroomid") int quizRoomId,
												@RequestBody PlayAnswerRequest request,
												@AuthenticationPrincipal CustomUserDetails userDetails) {
		int userId = userDetails.getId();
		
		PlayAnswerResponse response = playService.submitFinalAnswer(userId, quizRoomId, request);
		
		return ResponseEntity.ok(
		        ApiResponse.ok("채점 성공", response)
		);
	}
}
