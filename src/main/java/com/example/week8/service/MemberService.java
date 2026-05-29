package com.example.week8.service;

import com.example.week8.api.member.dto.MemberResponse;
import com.example.week8.entity.member.Member;
import com.example.week8.entity.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse getMyInfo() {
        // 현재 로그인한 유저의 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("인증 정보가 없습니다.");
        }

        // JWT 토큰에서 추출된 이메일(ID)
        String email = authentication.getName();

        // DB에서 이메일로 유저 찾기
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // DTO로 변환하여 반환
        return MemberResponse.from(member);
    }
}