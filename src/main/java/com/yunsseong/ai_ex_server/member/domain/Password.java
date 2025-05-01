package com.yunsseong.ai_ex_server.member.domain;

import jakarta.validation.constraints.NotBlank;

public record Password(@NotBlank(message = "닉네임을 입력해주세요") String password) {
}
