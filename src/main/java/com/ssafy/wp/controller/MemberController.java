package com.ssafy.wp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.wp.model.dto.Member;
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
			return ResponseEntity.ok(Map.of(
                    "message", "회원 정보 수정 성공",
                    "data", member));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			        .body(Map.of("message", "회원정보 찾을 수 없음"));
		}
	}
	
	@PostMapping // 로그인 기능으로 이동할 예정
	public ResponseEntity<Member> insert(@RequestBody Member member){
		
		int result = mService.insert(member);
		
		if (result > 0) {
			Member findMember = mService.select(member.getId());
			return ResponseEntity.status(HttpStatus.CREATED).body(findMember);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PatchMapping
	public ResponseEntity<?> update(@RequestBody Member member){
		
		int memberId =1;
		member.setId(memberId);
		int result = mService.update(member);
		
		if (result > 0) {
			Member updatedMember = mService.select(member.getId());
			return ResponseEntity.ok( Map.of(
                    "message", "회원 정보 수정 성공",
                    "data", updatedMember
            ));
		} else {
			return  ResponseEntity.badRequest()
                    .body(Map.of("message", "잘못된 입력"));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		
		int result = mService.delete(id);
		
		if (result > 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(Map.of("message", "탈퇴 성공"));
		} else {
			return  ResponseEntity.badRequest()
                    .body(Map.of("message", "잘못된 입력"));
		}
	}
}
