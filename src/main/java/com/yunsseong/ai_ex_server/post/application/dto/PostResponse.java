package com.yunsseong.ai_ex_server.post.application.dto;

import lombok.Builder;

@Builder
public record PostResponse(Long postId, String nickname, String title, String content, String createdAt, String updatedAt) {
}
