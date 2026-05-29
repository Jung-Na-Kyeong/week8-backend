package com.example.week8.entity.member;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일로 회원 조회 (로그인 및 정보 조회 시 사용)
    Optional<Member> findByEmail(String email);

    // 이메일 존재 여부 확인 (중복 확인 API용)
    boolean existsByEmail(String email);

    // 닉네임 존재 여부 확인 (회원가입 시 중복 체크용)
    boolean existsByNickname(String nickname);
}