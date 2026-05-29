package com.example.week8.global.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // @Valid 검증 실패 시 발생하는 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 명세서 예시 메시지가 있는 경우 해당 메시지 사용
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return createErrorResponse(ErrorCode.VALIDATION_FAILED, message);
    }

    // 비즈니스 로직(AuthService 등)에서 의도적으로 던진 예외 처리
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        ErrorCode errorCode;
        try {
            // throw new RuntimeException("ErrorCode명")으로 던졌을 때 매핑
            errorCode = ErrorCode.valueOf(e.getMessage());
        } catch (IllegalArgumentException ex) {
            // 정의되지 않은 에러는 500 에러로 처리
            errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        }
        return createErrorResponse(errorCode, errorCode.getMessage());
    }

    // 명세서 규격에 맞는 공통 에러 응답 생성
    private ResponseEntity<?> createErrorResponse(ErrorCode errorCode, String message) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(Map.of(
                        "status", "error", //
                        "message", message,
                        "code", errorCode.getCode()
                ));
    }
}