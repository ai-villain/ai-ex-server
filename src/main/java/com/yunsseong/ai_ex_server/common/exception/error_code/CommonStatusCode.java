package com.yunsseong.ai_ex_server.common.exception.error_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonStatusCode implements StatusConst {
    OK(2000, "성공적으로 처리되었습니다.", HttpStatus.OK),
    CREATED(2001, "생성되었습니다.", HttpStatus.CREATED),
    BAD_REQUEST(4000, "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    INVALID_INPUT_VALUE(4001, "입력값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(4010, "인증이 필요합니다.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN(4030, "권한이 없습니다.", HttpStatus.FORBIDDEN),
    NOT_FOUND(4040, "리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(5000, "서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_UNAVAILABLE(5030, "서비스를 사용할 수 없습니다.", HttpStatus.SERVICE_UNAVAILABLE);

    private final int statusCode;
    private final String message;
    private final HttpStatus httpStatus;
}