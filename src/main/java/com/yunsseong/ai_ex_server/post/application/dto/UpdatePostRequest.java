package com.yunsseong.ai_ex_server.post.application.dto;

public record UpdatePostRequest(Long postId, Long memberId, String title, String content) {
}
