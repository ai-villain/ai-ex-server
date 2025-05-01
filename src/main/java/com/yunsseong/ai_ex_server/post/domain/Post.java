package com.yunsseong.ai_ex_server.post.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Post(
        Long postId,
        Long userId,
        @NotNull Title title,
        @NotNull Content content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public boolean isCreatedBy(Long userId) {
        return this.userId.equals(userId);
    }

    public static PostBuilder builder() {
        return new PostBuilder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now());
    }
}
