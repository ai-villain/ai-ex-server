package com.yunsseong.ai_ex_server.common.exception;

import org.springframework.http.HttpStatus;

public interface StatusConst {
    HttpStatus getHttpStatus();
    String getMessage();
    int getStatusCode();
}
