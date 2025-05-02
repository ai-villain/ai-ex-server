package com.yunsseong.ai_ex_server.post.application.dto;

import lombok.Builder;

@Builder
public record CreatePostRequest(Long memberId, String title, String content) {
}
