package com.yunsseong.ai_ex_server.post.application.dto;

public record CreatePostRequest(Long userId, String title, String content) {
}
