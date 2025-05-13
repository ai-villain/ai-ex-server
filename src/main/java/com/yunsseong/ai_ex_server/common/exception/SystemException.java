package com.yunsseong.ai_ex_server.common.exception;

import lombok.Getter;

@Getter
public class SystemException extends RuntimeException{
    private final StatusConst statusConst;

    public SystemException(StatusConst statusConst) {
        super(statusConst.getMessage());
        this.statusConst = statusConst;
    }
}
