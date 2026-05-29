package com.example.week8.api.member;

import com.example.week8.api.member.dto.MemberResponse;
import com.example.week8.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "Member", description = "회원 관련 API")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    @Operation(summary = "내 정보 조회", description = "현재 로그인한 유저의 프로필 정보를 가져옵니다.")
    public ResponseEntity<?> getMyInfo() {
        MemberResponse data = memberService.getMyInfo();

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "내 정보 조회가 완료되었습니다.",
                "data", data
        ));
    }
}