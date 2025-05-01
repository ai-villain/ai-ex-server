package com.yunsseong.ai_ex_server.common.exception.error_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostErrorCode implements ErrorCode {
    EMPTY_TITLE(HttpStatus.BAD_REQUEST, "제목을 입력해주세요"),
    EMPTY_CONTENT(HttpStatus.BAD_REQUEST, "본문을 입력해주세요"),
    EXCEED_TITLE_LENGTH_LIMIT(HttpStatus.BAD_REQUEST, "제목은 100자를 넘을 수 없습니다."),
    EXCEED_CONTENT_LENGTH_LIMIT(HttpStatus.BAD_REQUEST, "본문은 500자를 넘을 수 없습니다."),
    NOT_FOUND_POST(HttpStatus.BAD_REQUEST, "글을 찾을 수 없습니다"),
    WRITER_MISMATCH(HttpStatus.BAD_REQUEST, "글 작성자가 아닙니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
