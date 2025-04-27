package com.yunsseong.ai_ex_server.post.domain;

public record Title(String title) {
    public Title {
        if (title == null) {
            throw new IllegalArgumentException("제목을 입력해주세요");
        }
        if (title.length() > 100) {
            throw new IllegalArgumentException("제목은 100자를 넘을 수 없습니다.");
        }
    }
}
