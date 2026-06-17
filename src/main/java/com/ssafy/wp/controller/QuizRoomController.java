package com.ssafy.wp.controller;

import java.util.List;
import java.util.Map;

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

import com.ssafy.wp.model.dto.QuizRoom;
import com.ssafy.wp.model.dto.QuizRoomCreateRequest;
import com.ssafy.wp.model.dto.QuizRoomDetailResponse;
import com.ssafy.wp.security.dto.CustomUserDetails;
import com.ssafy.wp.service.QuizRoomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "QuizRoom", description = "퀴즈룸 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quizroom")
public class QuizRoomController {
	
	private final QuizRoomService qrService;
	
	@Operation(
            summary = "교사 퀴즈룸 전체 조회",
            description = "JWT 토큰에서 교사 id를 꺼내 해당 교사가 생성한 퀴즈룸 목록을 조회"
    )
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
	
	@Operation(
            summary = "퀴즈룸 상세 조회",
            description = "특정 퀴즈룸의 기본 정보와 퀴즈 목록을 함께 조회"
    )
	@GetMapping("/{id}")
	public ResponseEntity<?> select(@Parameter(description = "조회할 퀴즈룸 id", example = "1") 
									@PathVariable("id") int id){
		
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
	
	@Operation(
            summary = "퀴즈룸 생성",
            description = "JWT 토큰에서 교사 id를 꺼내 퀴즈룸을 생성"
    )
	@PostMapping
	public ResponseEntity<?> insert(@AuthenticationPrincipal CustomUserDetails userDetails,
									@RequestBody QuizRoomCreateRequest request) {

		String errorMessage = validateRequest(request);

        if (errorMessage != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", errorMessage
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
	
	@Operation(
            summary = "퀴즈룸 수정",
            description = "퀴즈룸 기본 정보를 수정하고, 기존 퀴즈 목록 연결을 삭제한 뒤 요청으로 들어온 quizList로 전체 교체"
    )
	@PatchMapping("/{id}")
	public ResponseEntity<?> update(@AuthenticationPrincipal CustomUserDetails userDetails,
									@Parameter(description = "수정할 퀴즈룸 id", example = "1")
									@PathVariable("id") int id,
									@RequestBody QuizRoomCreateRequest request){

		String errorMessage = validateRequest(request);

        if (errorMessage != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", errorMessage
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
	
	@Operation(
            summary = "퀴즈룸 삭제",
            description = "JWT 토큰에서 교사 id를 꺼내 해당 교사가 소유한 퀴즈룸만 삭제합니다."
    )
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@AuthenticationPrincipal CustomUserDetails userDetails,
						            @Parameter(description = "삭제할 퀴즈룸 id", example = "1")						
									@PathVariable("id") int id) {

		int userId = userDetails.getId();
		int result = qrService.delete(id, userId);

		if (result > 0) {
			return ResponseEntity.ok(Map.of(
					"message", "퀴즈룸 삭제 성공"
			));
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
				"message", "퀴즈룸을 찾을 수 없거나 삭제 권한이 없습니다."
		));
	}
	
	private String validateRequest(QuizRoomCreateRequest request) {
        if (request == null) {
            return "요청 본문이 비어 있습니다.";
        }
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            return "제목을 입력해야 합니다.";
        }
        if (request.getState() == null || request.getState().isBlank()) {
            return "상태를 입력해야 합니다.";
        }
        if (request.getLevel() == null || request.getLevel().isBlank()) {
            return "난이도를 입력해야 합니다.";
        }
        if (request.getDescription() == null || request.getDescription().isBlank()) {
            return "설명을 입력해야 합니다.";
        }
        if (request.getQuizList() == null || request.getQuizList().isEmpty()) {
            return "퀴즈를 최소 1개 이상 선택해야 합니다.";
        }
        return null;
    }
}
