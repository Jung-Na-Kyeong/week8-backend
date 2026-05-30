package com.example.week8.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 400 Bad Request
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "요청 데이터 유효성 검증에 실패했습니다.", "VALIDATION_FAILED"),
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "이메일 형식이 올바르지 않습니다.", "INVALID_EMAIL_FORMAT"),

    // 401 Unauthorized
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다.", "UNAUTHORIZED_USER"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.", "INVALID_TOKEN"),

    // 409 Conflict
    ALREADY_EXIST_EMAIL(HttpStatus.CONFLICT, "이미 가입된 이메일입니다.", "ALREADY_EXIST_EMAIL"),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.", "INTERNAL_SERVER_ERROR");

    private final HttpStatus status;
    private final String message;
    private final String code;
}