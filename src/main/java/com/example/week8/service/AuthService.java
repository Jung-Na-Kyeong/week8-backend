package com.example.week8.service;

import com.example.week8.api.auth.dto.JoinRequest;
import com.example.week8.api.auth.dto.LoginRequest;
import com.example.week8.entity.member.Member;
import com.example.week8.entity.member.MemberRepository;
import com.example.week8.entity.member.Role;
import com.example.week8.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    // 사용자 이메일과 비밀번호로 새로운 계정 생성
    @Transactional
    public void signup(JoinRequest request) {
        // 이메일 중복 체크 및 예외 처리
        if (memberRepository.existsByEmail(request.email())) {
            throw new RuntimeException("ALREADY_EXIST_EMAIL");
        }

        // 닉네임 중복 체크 (DB 유니크 제약 조건 준수)
        if (memberRepository.existsByNickname(request.nickname())) {
            throw new RuntimeException("ALREADY_EXIST_NICKNAME");
        }

        // 비밀번호 암호화 필수 적용
        Member member = Member.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .name(request.name())
                .nickname(request.nickname())
                .role(Role.USER)
                .build();

        memberRepository.save(member);
    }

    // 로그인 [cite: 70-72]
    // 인증 후 JWT Access Token 발급
    public String login(LoginRequest request) {
        // 가입되지 않은 이메일 또는 비밀번호 불일치 시 인증 실패 처리
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("UNAUTHORIZED_USER"));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new RuntimeException("UNAUTHORIZED_USER");
        }

        // 성공 시 JWT Access Token 반환
        return jwtTokenProvider.createToken(member.getEmail());
    }

    // 이메일 중복 확인
    public boolean checkEmail(String email) {
        // 이미 가입된 이메일인 경우 Conflict 예외 발생
        if (memberRepository.existsByEmail(email)) {
            throw new RuntimeException("ALREADY_EXIST_EMAIL");
        }
        return true; // 사용 가능한 이메일
    }

    // 로그아웃
    @Transactional
    public void logout() {
        // 현재 로그인된 사용자의 토큰 무효화 처리 수행
    }
}