package com.example.week8.global.jwt;

import jakarta.annotation.Nonnull; // 최신 표준인 jakarta 패키지로 교체
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// 모든 HTTP 요청마다 한 번씩 실행되며, JWT 토큰의 유효성을 검증하는 필터
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain) throws ServletException, IOException {

        // 헤더에서 'Authorization' 토큰 추출
        String token = jwtTokenProvider.resolveToken(request);

        // 토큰이 존재하고 유효한 경우 인증 정보 설정
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 유효하다면 인증 객체(Authentication) 생성
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            // SecurityContext에 저장하여 시스템 전체에서 유저 인식 가능하게 함
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // 다음 필터로 요청을 넘김
        filterChain.doFilter(request, response);
    }
}