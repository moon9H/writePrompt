package com.ssafy.wp.controller;

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
import com.ssafy.wp.model.dto.member.Member;
import com.ssafy.wp.model.dto.member.MemberDetailResponse;
import com.ssafy.wp.model.dto.member.MemberRequest;
import com.ssafy.wp.model.dto.member.MemberResponse;
import com.ssafy.wp.security.dto.CustomUserDetails;
import com.ssafy.wp.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Member", description = "회원 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
	
	private final MemberService mService;
	
	@Operation(
	    summary = "회원 조회",
	    description = "JWT 토큰에서 사용자 id를 꺼내 로그인한 회원 정보 조회"
	)
	@GetMapping("/me")
	public ResponseEntity<?> select(@AuthenticationPrincipal CustomUserDetails userDetails){
		int id = userDetails.getId();
		MemberDetailResponse member = mService.selectDetail(id);
		
		if (member != null) {
		    return ResponseEntity.ok(
		            ApiResponse.ok("회원 정보 조회 성공", member)
		    );
		} else {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
		            ApiResponse.fail("회원정보 찾을 수 없음")
		    );
		}
	}
	
	@Operation(
        summary = "회원가입",
        description = "사용자 정보를 입력받아 신규 회원 등록"
	)
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody MemberRequest request){
		
		Member member = mService.insert(request);
		
		if (member != null) {
		    return ResponseEntity.status(HttpStatus.CREATED).body(
		            ApiResponse.ok("회원가입 성공", MemberResponse.from(member))
		    );
		} else {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
		            ApiResponse.fail("잘못된 입력")
		    );
		}
	}
	
	@Operation(
        summary = "회원 정보 수정",
        description = "JWT 토큰에서 사용자 id를 꺼내 로그인한 회원 정보 수정"
	)
	@PatchMapping
	public ResponseEntity<?> update(@AuthenticationPrincipal CustomUserDetails userDetails,
	                                @RequestBody MemberRequest request){
		
		int memberId = userDetails.getId();
		Member updatedMember = mService.update(memberId, request);
		
		if (updatedMember != null) {
		    return ResponseEntity.ok(
		            ApiResponse.ok("회원 정보 수정 성공", MemberResponse.from(updatedMember))
		    );
		} else {
		    return ResponseEntity.badRequest().body(
		            ApiResponse.fail("잘못된 입력")
		    );
		}
	}
	
	@Operation(
        summary = "회원 탈퇴",
        description = "회원 id를 기준으로 회원 정보 삭제"
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@AuthenticationPrincipal CustomUserDetails userDetails,
									@PathVariable("id") int id){
		
		if (id != userDetails.getId()) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
	                ApiResponse.fail("삭제 권한이 없습니다.")
	        );
	    }
		
		int result = mService.delete(id);
		
		if (result > 0) {
		    return ResponseEntity.ok(
		            ApiResponse.ok("탈퇴 성공")
		    );
		} else {
		    return ResponseEntity.badRequest().body(
		            ApiResponse.fail("잘못된 입력")
		    );
		}
	}
}
