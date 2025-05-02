package com.yunsseong.ai_ex_server.member.application.dto;

import lombok.Builder;

@Builder
public record CreateMemberRequest(String nickname, String email, String password) {
}
