package com.example.week8.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//로그인 요청 데이터
public record LoginRequest(
        @NotBlank(message = "이메일을 입력해 주세요.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email, // 가입 시 사용한 이메일

        @NotBlank(message = "비밀번호를 입력해 주세요.")
        @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
        String password // 계정 비밀번호
) {}