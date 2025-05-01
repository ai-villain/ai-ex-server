package com.yunsseong.ai_ex_server.common.exception.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(int status, String error, String message, LocalDateTime timestamp) {

    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder()
                .timestamp(LocalDateTime.now());
    }
}
