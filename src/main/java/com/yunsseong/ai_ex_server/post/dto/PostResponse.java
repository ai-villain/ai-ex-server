package com.yunsseong.ai_ex_server.post.dto;

import lombok.Builder;

@Builder
public record PostResponse(Long postId, String nickname, String title, String content, Long likeCount, String createdAt, String updatedAt) {
}
