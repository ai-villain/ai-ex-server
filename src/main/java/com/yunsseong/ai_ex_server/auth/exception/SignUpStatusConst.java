package com.yunsseong.ai_ex_server.auth.exception;

import com.yunsseong.ai_ex_server.common.exception.StatusConst;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SignUpStatusConst implements StatusConst {
    PASSWORDS_MISMATCH(4301, "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    ALREADY_EXIST_EMAIL(4302, "이미 존재하는 이메일 입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_EXIST_NICKNAME(4303, "이미 존재하는 닉네임 입니다.", HttpStatus.BAD_REQUEST);

    private final int statusCode;
    private final String message;
    private final HttpStatus httpStatus;
}