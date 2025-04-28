package com.yunsseong.ai_ex_server.member.domain;

public record Nickname(String nickname) {
    public Nickname {
        if (nickname == null) {
            throw new IllegalArgumentException();
        }
    }
}
