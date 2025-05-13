package com.yunsseong.ai_ex_server.common.dto;

import com.yunsseong.ai_ex_server.common.exception.StatusConst;
import lombok.Getter;

import static com.yunsseong.ai_ex_server.common.exception.CommonStatusCode.OK;


@Getter
public class ApiResponse<T> {
    private final int statusCode;
    private final String message;
    private final T data;

    private ApiResponse(Integer statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(OK.getStatusCode(), OK.getMessage(), null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(OK.getStatusCode(), OK.getMessage(), data);
    }

    public static <T> ApiResponse<T> failure(StatusConst statusConst, T data) {
        return new ApiResponse<>(statusConst.getStatusCode(), statusConst.getMessage(), data);
    }

    public static <T> ApiResponse<T> failure(StatusConst statusConst) {
        return new ApiResponse<>(statusConst.getStatusCode(), statusConst.getMessage(), null);
    }
}