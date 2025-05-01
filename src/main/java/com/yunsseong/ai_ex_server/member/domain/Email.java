package com.yunsseong.ai_ex_server.member.domain;

import jakarta.validation.constraints.NotBlank;

public record Email(@NotBlank(message = "이메일을 입력해주세요") String email) {
}
