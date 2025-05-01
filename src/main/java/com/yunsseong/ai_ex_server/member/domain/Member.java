package com.yunsseong.ai_ex_server.member.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record Member(Long userId, @NotNull Nickname nickname, @NotNull Email email, @NotNull Password password) {
}
