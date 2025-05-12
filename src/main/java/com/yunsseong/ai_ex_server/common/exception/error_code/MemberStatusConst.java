package com.yunsseong.ai_ex_server.common.exception.error_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberStatusConst implements StatusConst {
    NOT_FOUND_MEMBER(4201, "유저를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);

    private final int statusCode;
    private final String message;
    private final HttpStatus httpStatus;
}