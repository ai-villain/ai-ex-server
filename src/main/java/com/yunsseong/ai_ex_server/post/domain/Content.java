package com.yunsseong.ai_ex_server.post.domain;

public record Content(String content) {
    public Content {
        if (content == null) {
            throw new IllegalArgumentException("내용을 입력해주세요");
        }
        if  (content.length() > 500) {
            throw new IllegalArgumentException("내용은 500자를 넘을 수 없습니다.");
        }
    }
}
