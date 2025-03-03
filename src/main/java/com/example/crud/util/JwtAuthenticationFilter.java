package com.example.crud.util;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
// JWT를 검증하고 SecurityContext에 사용자 인증 정보 저장
// HTTP 요청의 Authorization 헤더에서 토큰 추출 및 검증 -> 유효한 토큰이면 SecurityContext에 인증 정보 저장

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        String requestURI = request.getRequestURI(); // 요청 URI 가져오기
//
//        // 인증이 필요 없는 엔드포인트 필터링 제외
//        if (requestURI.equals("/user/register") || requestURI.equals("/user/login")) {
//            filterChain.doFilter(request, response); // 필터링 건너뛰기
//            return;
//        }

        // Bearer 제거한 순수 토큰 추출
        String token = jwtUtil.resolveToken(request);

        // null이 아니고 유효한토큰이면
        if (token != null) {
            try {
                if (jwtUtil.validateToken(token)) {
                    String email = jwtUtil.getEmailFromToken(token);    // 순수토큰으로 이메일 추출
                    System.out.println("Email:" +email);

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());

                    // SecurityContext에 사용자 인증정보 저장
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (JwtException e) {
                // JWT 검증 실패 시 401 Unauthorized 반환
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("유효하지 않은 JWT 토큰입니다.");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}

