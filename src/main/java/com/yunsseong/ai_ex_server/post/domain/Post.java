package com.yunsseong.ai_ex_server.post.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Post(Long postId, Long userId, Title title, Content content, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public Post {
        if (title == null || content == null) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isCreatedBy(Long userId) {
        return this.userId.equals(userId);
    }

    public static PostBuilder builder() {
        return new PostBuilder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now());
    }
}
