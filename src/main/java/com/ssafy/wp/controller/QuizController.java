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

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
public class QuizController {
	
	private final QuizService qService;
	
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
