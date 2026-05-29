package com.example.week8.api.auth;

import com.example.week8.api.auth.dto.JoinRequest;
import com.example.week8.api.auth.dto.LoginRequest;
import com.example.week8.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody JoinRequest request) {
        authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "status", 201,
                "message", "회원가입이 완료되었습니다.",
                "data", Map.of(
                        "email", request.email(),
                        "name", request.name(),
                        "nickname", request.nickname(),
                        "createdAt", LocalDateTime.now().toString()
                )
        ));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        String accessToken = authService.login(request);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "로그인에 성공했습니다.",
                "data", Map.of("accessToken", accessToken)
        ));
    }

    // 이메일 중복 확인
    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean isAvailable = authService.checkEmail(email);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "사용 가능한 이메일입니다.",
                "data", Map.of("isAvailable", isAvailable)
        ));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        authService.logout();

        // Map.of() 대신 HashMap을 사용하여 null 값을 허용
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "성공적으로 로그아웃 되었습니다.");
        response.put("data", null);

        return ResponseEntity.ok(response);
    }
}