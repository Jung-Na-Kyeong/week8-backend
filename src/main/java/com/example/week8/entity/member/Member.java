package com.example.week8.entity.member;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email; // 로그인 및 계정 식별용 이메일

    @Column(nullable = false)
    private String password; // 암호화된 비밀번호

    @Column(nullable = false)
    private String name; // 사용자 본명

    @Column(nullable = false, unique = true)
    private String nickname; // 서비스 내 별명

    @Enumerated(EnumType.STRING)
    private Role role; // 권한 (USER/ADMIN)

    private LocalDateTime createdAt; // 계정 생성일시

    @Builder
    public Member(String email, String password, String name, String nickname, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.role = (role != null) ? role : Role.USER;
        this.createdAt = LocalDateTime.now();
    }
}