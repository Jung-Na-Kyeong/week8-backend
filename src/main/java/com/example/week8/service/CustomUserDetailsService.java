package com.example.week8.service;

import com.example.week8.entity.member.Member;
import com.example.week8.entity.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import jakarta.annotation.Nonnull; // 👈 최신 자바 표준(jakarta)으로 변경!
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Nonnull // 👈 대소문자 주의 (NonNull -> Nonnull)
    @Override
    public UserDetails loadUserByUsername(@Nonnull String email) throws UsernameNotFoundException {
        // 1. DB에서 이메일로 유저를 찾습니다.
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일을 가진 유저를 찾을 수 없습니다: " + email));

        // 2. 스프링 시큐리티가 이해할 수 있는 UserDetails 객체로 변환해서 돌려줍니다.
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();
    }
}