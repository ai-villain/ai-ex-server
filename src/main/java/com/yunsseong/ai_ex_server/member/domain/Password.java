package com.yunsseong.ai_ex_server.member.domain;

public record Password(String password) {
    public Password {
        if (password == null) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요");
        }
    }
}
