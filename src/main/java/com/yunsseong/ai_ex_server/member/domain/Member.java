package com.yunsseong.ai_ex_server.member.domain;

import lombok.Builder;

@Builder
public record Member(Long userId, Nickname nickname, Email email, Password password) {
}
