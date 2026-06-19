package com.ssafy.wp.controller;

import java.util.Map;

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
import com.ssafy.wp.model.dto.member.Member;
import com.ssafy.wp.security.dto.CustomUserDetails;
import com.ssafy.wp.security.jwt.JWTUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        Member member = userDetails.getMember();

        String accessToken = jwtUtil.createAccessToken(member);

        return ResponseEntity.ok(
                ApiResponse.ok("로그인 성공", Map.of(
                        "accessToken", accessToken,
                        "member", Map.of(
                                "id", member.getId(),
                                "email", member.getEmail(),
                                "nickname", member.getNickname(),
                                "role", member.getRole()
                        )
                ))
        );
    }
}