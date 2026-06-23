package com.ssafy.wp.security.filter;

import java.io.IOException;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.ssafy.wp.security.jwt.JWTUtil;
import com.ssafy.wp.security.service.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTVerificationFilter extends OncePerRequestFilter {
	private final JWTUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
	
	/**
	 * Authorization 헤더에서 토큰을 추출한다.
	 */
	private String extractToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null && token.startsWith("Bearer ")) {
			String accessToken = token.substring(7).trim();
			return accessToken.isBlank() ? null : accessToken;
		}
		return null;

		// END
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String method = request.getMethod();
		String path = request.getServletPath();

		return HttpMethod.OPTIONS.matches(method)
				|| (HttpMethod.POST.matches(method) && isSamePath(path, "/api/auth/login"))
				|| (HttpMethod.POST.matches(method) && isSamePath(path, "/api/members"))
				|| (HttpMethod.POST.matches(method) && isSamePath(path, "/api/auth/refresh"))
				|| (HttpMethod.POST.matches(method) && isSamePath(path, "/api/auth/logout"))
				|| "/".equals(path)
				|| "/index.html".equals(path)
				|| "/error".equals(path)
				|| path.startsWith("/swagger-ui")
				|| path.startsWith("/v3/api-docs");
		
	}

	private boolean isSamePath(String actualPath, String expectedPath) {
		return expectedPath.equals(actualPath) || (expectedPath + "/").equals(actualPath);
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// JWT 토큰을 검증하고 인증 정보를 SecurityContextHolder에 저장한다.
		String token = extractToken(request);
		// 01. login 등 인증 토큰이 없는 요청은 인증을 하지 않고 다음 필터로 보낸다.
		if (token == null) {
			filterChain.doFilter(request, response);
			return;
		}
		// 2. 토큰 검증 및 사용자 정보 추출 - 토큰에 문제 없다면(clame 조회 시 예외 없음) 사용자 정보는 신뢰할만하다.
		try {
			Claims claims = jwtUtil.getClaims(token);
			int memberId = Integer.parseInt(claims.getSubject());
			UserDetails userDetails = userDetailsService.loadUserById(memberId);
		// 3. UsernamePasswordAuthenticationToken 생성 및 SecurityContextHolder에 저장
			var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
		// 4. 다음 filter로 요청을 전달
			filterChain.doFilter(request, response);
		} catch (JwtException | IllegalArgumentException e) {
			log.warn("Invalid JWT token: {}", e.getMessage());
			SecurityContextHolder.clearContext();
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
		}
		// END
	}

}
