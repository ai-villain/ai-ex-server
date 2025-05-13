package com.yunsseong.ai_ex_server.common.exception;

import com.yunsseong.ai_ex_server.common.exception.error_code.StatusConst;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private final StatusConst statusConst;

    public BusinessException(StatusConst statusConst) {
        super(statusConst.getMessage());
        this.statusConst = statusConst;
    }
}
