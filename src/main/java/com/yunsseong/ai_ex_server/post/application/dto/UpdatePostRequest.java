package com.yunsseong.ai_ex_server.post.application.dto;

public record UpdatePostRequest(Long postId, Long userId, String title, String content) {
}
