package com.ssafy.wp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.wp.common.response.ApiResponse;
import com.ssafy.wp.model.dto.quiz.Quiz;
import com.ssafy.wp.security.dto.CustomUserDetails;
import com.ssafy.wp.service.QuizService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Quiz", description = "퀴즈 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
public class QuizController {
	
	private final QuizService qService;

	@Operation(
        summary = "퀴즈 조회",
        description = "퀴즈 id를 기준으로 특정 퀴즈 정보 조회"
	)
	@GetMapping("/{id}")
	public ResponseEntity<?> select(@PathVariable("id") int id){
		Quiz quiz = qService.select(id);
		
		if (quiz != null) {
		    return ResponseEntity.ok(
		            ApiResponse.ok("퀴즈 조회 성공", quiz)
		    );
		} else {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
		            ApiResponse.fail("찾을 수 없음")
		    );
		}
	}
	
	@Operation(
        summary = "교사 퀴즈 전체 조회",
        description = "JWT 토큰에서 교사 id를 꺼내 해당 교사가 생성한 모든 퀴즈 조회"
	)
	@GetMapping
	public ResponseEntity<?> selectAll(@AuthenticationPrincipal CustomUserDetails userDetails){
		
		int userId = userDetails.getId();
		List<Quiz> qList = qService.selectAll(userId);
		
		if (qList != null) {
		    return ResponseEntity.ok(
		            ApiResponse.ok("퀴즈 전체 조회 성공", qList)
		    );
		} else {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
		            ApiResponse.fail("잘못된 요청")
		    );
		}
	}
	
	@Operation(
        summary = "퀴즈 생성",
        description = "교사가 새로운 퀴즈 생성"
	)
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Quiz quiz){

		int result = qService.insert(quiz);
		
		if (result > 0) {
		    Quiz findQuiz = qService.select(quiz.getId());
		    return ResponseEntity.status(HttpStatus.CREATED).body(
		            ApiResponse.ok("퀴즈 생성 성공", findQuiz)
		    );
		} else {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
		            ApiResponse.fail("잘못된 요청")
		    );
		}
	}
	
	@Operation(
        summary = "퀴즈 수정",
        description = "퀴즈 id를 기준으로 퀴즈 제목 수정"
	)
	@PatchMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, 
									@RequestBody String title) {
		
		int result = qService.update(id, title);
		
		if (result > 0) {
		    Quiz findQuiz = qService.select(id);
		    return ResponseEntity.ok(
		            ApiResponse.ok("퀴즈 수정 성공", findQuiz)
		    );
		} else {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
		            ApiResponse.fail("찾을 수 없음")
		    );
		}
	}
	
	@Operation(
        summary = "퀴즈 삭제",
        description = "퀴즈 id를 기준으로 생성된 퀴즈 삭제"
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		
		int result = qService.delete(id);
		
		if (result > 0) {
		    return ResponseEntity.status(HttpStatus.OK).body(
		            ApiResponse.ok("퀴즈 삭제 성공")
		    );
		} else {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
		            ApiResponse.fail("찾을 수 없음")
		    );
		}
	}
}
