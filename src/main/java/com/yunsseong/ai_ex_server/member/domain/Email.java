package com.yunsseong.ai_ex_server.member.domain;

public record Email(String email) {
    public Email {
        if (email == null) {
            throw new IllegalArgumentException("이메일을 입력해주세요");
        }
    }
}
