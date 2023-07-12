package com.atemos.sample.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.atemos.sample.entity.AuthToken;
import com.atemos.sample.exception.ErrorCode;
import com.atemos.sample.exception.GlobalException;
import com.atemos.sample.service.AuthService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private final AuthService authService;
	
	public JwtAuthorizationFilter(  AuthService authService) {
		
		this.authService = authService;
	}

	private String extractToken(HttpServletRequest request) {
	    String bearerToken = request.getHeader("Authorization");
//	    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//	        return bearerToken.substring(7); // "Bearer " 이후의 토큰 부분 반환
//	    }
	    return bearerToken;
//	    return null;
	}
	
	@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
				FilterChain filterChain) throws ServletException, IOException {
			// TODO Auto-generated method stub
			try {
				String token = extractToken(request);
				if(token != null) {
					AuthToken dbToken = authService.hasToken(token);
					if(dbToken !=null) {
						filterChain.doFilter(request, response);
					}
				}else {
					// 인증 실패
		            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid authentication token");
//					throw new GlobalException("UNAUTHORIZED error", ErrorCode.UNAUTHORIZED);
				}
			} catch(Exception e) {
				// TODO: handle finally clause
				log.error(ALREADY_FILTERED_SUFFIX +", "+ e.toString());
			}
		}

}
