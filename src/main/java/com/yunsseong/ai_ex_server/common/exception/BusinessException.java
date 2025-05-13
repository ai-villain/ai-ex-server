package com.yunsseong.ai_ex_server.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private final StatusConst statusConst;

    public BusinessException(StatusConst statusConst) {
        super(statusConst.getMessage());
        this.statusConst = statusConst;
    }
}
