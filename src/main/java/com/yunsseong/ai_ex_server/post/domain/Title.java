package com.yunsseong.ai_ex_server.post.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record Title(
        @NotNull(message = "제목을 입력해주세요")
        @Size(max = 100, message = "제목은 100자를 넘을 수 없습니다.")
        String title
) {
}
