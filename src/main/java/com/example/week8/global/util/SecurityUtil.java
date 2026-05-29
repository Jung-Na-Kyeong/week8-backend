package com.example.week8.global.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

// Spring Security의 인증 정보를 활용하기 위한 유틸리티 클래스
public class SecurityUtil {

    // 인스턴스화 방지
    private SecurityUtil() {
    }

     // 현재 인증된 유저의 이메일 반환
     // @return 현재 유저의 이메일
     // @throws RuntimeException 인증 정보가 없는 경우 발생
    public static String getCurrentMemberEmail() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            // "내 정보 조회" 명세서의 인증 실패 상황에 대응하는 예외 처리
            throw new RuntimeException("INVALID_TOKEN");
        }

        return authentication.getName();
    }
}