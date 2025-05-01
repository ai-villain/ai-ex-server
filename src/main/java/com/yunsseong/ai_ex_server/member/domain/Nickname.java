package com.yunsseong.ai_ex_server.member.domain;

import jakarta.validation.constraints.NotBlank;

public record Nickname(@NotBlank(message = "닉네임을 입력해주세요") String nickname) {
}
