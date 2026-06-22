package com.ssafy.wp.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.wp.security.dto.CustomUserDetails;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestLogInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(RequestLogInterceptor.class);

	private static final String START_TIME = "startTime";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		request.setAttribute(START_TIME, System.currentTimeMillis());

		log.info("[REQUEST] {} {} user={}",
				request.getMethod(),
				request.getRequestURI(),
				getUserId());

		return true;
	}

	@Override
	public void afterCompletion(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler,
			Exception ex) {

		Long startTime = (Long) request.getAttribute(START_TIME);
		long duration = startTime == null ? 0 : System.currentTimeMillis() - startTime;

		if (ex == null) {
			log.info("[RESPONSE] {} {} status={} time={}ms user={}",
					request.getMethod(),
					request.getRequestURI(),
					response.getStatus(),
					duration,
					getUserId());
		} else {
			log.error("[ERROR] {} {} status={} time={}ms user={} error={}",
					request.getMethod(),
					request.getRequestURI(),
					response.getStatus(),
					duration,
					getUserId(),
					ex.getMessage());
		}
	}

	private Object getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return "anonymous";
		}

		Object principal = authentication.getPrincipal();

		if (principal instanceof CustomUserDetails userDetails) {
			return userDetails.getId();
		}

		return "anonymous";
	}
}