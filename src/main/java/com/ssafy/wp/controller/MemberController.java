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
import com.ssafy.wp.security.dto.CustomUserDetails;
import com.ssafy.wp.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
	
	private final MemberService mService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> select(@PathVariable("id") int id){
		Member member = mService.select(id);
		
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
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Member member){
		
		int result = mService.insert(member);
		
		if (result > 0) {
		    Member findMember = mService.select(member.getId());
		    return ResponseEntity.status(HttpStatus.CREATED).body(
		            ApiResponse.ok("회원가입 성공", findMember)
		    );
		} else {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
		            ApiResponse.fail("잘못된 입력")
		    );
		}
	}
	
	@PatchMapping
	public ResponseEntity<?> update(@AuthenticationPrincipal CustomUserDetails userDetails,
	                                @RequestBody Member member){
		
		int memberId = userDetails.getId();
		member.setId(memberId);
		
		int result = mService.update(member);
		
		if (result > 0) {
		    Member updatedMember = mService.select(member.getId());
		    return ResponseEntity.ok(
		            ApiResponse.ok("회원 정보 수정 성공", updatedMember)
		    );
		} else {
		    return ResponseEntity.badRequest().body(
		            ApiResponse.fail("잘못된 입력")
		    );
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		
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
