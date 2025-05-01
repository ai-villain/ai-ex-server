package com.yunsseong.ai_ex_server.post.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Content(
        @NotBlank(message = "본문을 입력해주세요")
        @Size(max = 500, message = "본문은 500자를 넘을 수 없습니다.")
        String content
) {
}
