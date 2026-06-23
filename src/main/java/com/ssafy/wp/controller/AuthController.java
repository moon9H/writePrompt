package com.ssafy.wp.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.wp.common.response.ApiResponse;
import com.ssafy.wp.model.dto.auth.LoginRequest;
import com.ssafy.wp.model.dto.auth.RefreshRequest;
import com.ssafy.wp.model.dto.member.Member;
import com.ssafy.wp.security.dto.CustomUserDetails;
import com.ssafy.wp.security.jwt.JWTUtil;
import com.ssafy.wp.service.MemberService;

import io.jsonwebtoken.JwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Auth", description = "인증 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final MemberService memberService;
	private final JWTUtil jwtUtil;

	@Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인하고 JWT Access Token 발급")
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		Member member = userDetails.getMember();

		String accessToken = jwtUtil.createAccessToken(member);
		String refreshToken = jwtUtil.createRefreshToken(member);

		memberService.updateRefreshToken(member.getId(), refreshToken);

		return ResponseEntity.ok(ApiResponse.ok("로그인 성공",
				Map.of("accessToken", accessToken, "refreshToken", refreshToken, "member", Map.of("id", member.getId(),
						"email", member.getEmail(), "nickname", member.getNickname(), "role", member.getRole()))));
	}
	
	@Operation(
		    summary = "Access Token 재발급",
		    description = "Refresh Token을 검증하고 새로운 Access Token을 발급합니다."
		)
	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(@RequestBody RefreshRequest refreshRequest){
    	String refreshToken =refreshRequest.getRefreshToken();
    	
    	try {
    		int memberId = jwtUtil.getMemberId(refreshToken);
    		Member member =memberService.select(memberId);
    		String newAccessToken =jwtUtil.createAccessToken(member);
    		
    		return ResponseEntity.ok(
    				ApiResponse.ok("Access Token 재발급 성공", Map.of(
                            "accessToken", newAccessToken
    				))
    				);
    	}
    	catch (JwtException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.fail("유효하지 않은 Refresh Token입니다."));
        }
    }
	
	
	@Operation(
		    summary = "로그아웃",
		    description = "Refresh Token을 DB에서 삭제합니다."
		)
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestBody(required = false) RefreshRequest request) {
	    if (request != null
	            && request.getRefreshToken() != null
	            && !request.getRefreshToken().isBlank()) {
	        try {
	            int memberId = jwtUtil.getMemberId(request.getRefreshToken());
	            memberService.deleteRefreshToken(memberId);
	        } catch (JwtException | IllegalArgumentException ignored) {
	        }
	    }

	    return ResponseEntity.ok(ApiResponse.ok("로그아웃 성공"));
	}

}