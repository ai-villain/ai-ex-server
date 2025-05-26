package com.yunsseong.ai_ex_server.post.dto;

import lombok.Builder;

@Builder
public record LikeResponse(boolean isLiked, Long likeCount) {
}
