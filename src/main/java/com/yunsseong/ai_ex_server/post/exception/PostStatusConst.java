package com.yunsseong.ai_ex_server.post.exception;

import com.yunsseong.ai_ex_server.common.exception.StatusConst;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostStatusConst implements StatusConst {
    EMPTY_TITLE(4101, "제목을 입력해주세요", HttpStatus.BAD_REQUEST),
    EMPTY_CONTENT(4102, "본문을 입력해주세요", HttpStatus.BAD_REQUEST),
    EXCEED_TITLE_LENGTH_LIMIT(4103, "제목은 100자를 넘을 수 없습니다.", HttpStatus.BAD_REQUEST),
    EXCEED_CONTENT_LENGTH_LIMIT(4104, "본문은 500자를 넘을 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_POST(4105, "글을 찾을 수 없습니다", HttpStatus.BAD_REQUEST),
    WRITER_MISMATCH(4106, "글 작성자가 아닙니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_POST_LIKE(4107, "해당 조건에 맞는 좋아요를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);

    private final int statusCode;
    private final String message;
    private final HttpStatus httpStatus;
}
