package com.example.week8.api.member.dto;

import com.example.week8.entity.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponse {
    private String email;
    private String name;
    private String nickname;

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .email(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .build();
    }
}