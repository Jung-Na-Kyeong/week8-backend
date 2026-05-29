package com.example.week8.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// 회원가입 요청 데이터
public record JoinRequest(
        @NotBlank(message = "이메일은 필수 입력 항목입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email, // 로그인 및 계정 식별용 [cite: 160]

        @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
                message = "비밀번호는 영문과 숫자를 포함하여 8자 이상이어야 합니다.")
        String password, // 계정 비밀번호 (암호화 필수)

        @NotBlank(message = "이름은 필수 입력 항목입니다.")
        @Size(min = 2, message = "이름은 2자 이상이어야 합니다.")
        String name, // 사용자 본명

        @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
        @Size(min = 2, max = 10, message = "닉네임은 2~10자 사이여야 합니다.")
        @Pattern(regexp = "^[a-zA-Z0-9가-힣]*$", message = "닉네임에 특수문자를 사용할 수 없습니다.")
        String nickname // 서비스 내 별명
) {}