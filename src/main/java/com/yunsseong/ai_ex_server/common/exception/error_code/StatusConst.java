package com.yunsseong.ai_ex_server.common.exception.error_code;

import org.springframework.http.HttpStatus;

public interface StatusConst {
    HttpStatus getHttpStatus();
    String getMessage();
    int getStatusCode();
}
