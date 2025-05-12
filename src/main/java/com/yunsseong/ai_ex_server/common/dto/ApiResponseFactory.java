package com.yunsseong.ai_ex_server.common.dto;

import com.yunsseong.ai_ex_server.common.exception.error_code.StatusConst;
import org.springframework.http.ResponseEntity;

public class ApiResponseFactory {
    public static ResponseEntity<ApiResponse<Void>> success() {
        return ResponseEntity.ok(ApiResponse.success());
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    public static ResponseEntity<ApiResponse<Void>> bizException(StatusConst statusConst) {
        return ResponseEntity
                .status(statusConst.getHttpStatus())
                .body(ApiResponse.failure(statusConst));
    }

    public static <T> ResponseEntity<ApiResponse<T>> bizException(StatusConst statusConst, T data) {
        return ResponseEntity
                .status(statusConst.getHttpStatus())
                .body(ApiResponse.failure(statusConst, data));
    }

    public static ResponseEntity<ApiResponse<Void>> sysException(StatusConst statusConst) {
        return ResponseEntity
                .status(statusConst.getHttpStatus())
                .body(ApiResponse.failure(statusConst));
    }

    public static <T> ResponseEntity<ApiResponse<T>> sysException(StatusConst statusConst, T data) {
        return ResponseEntity
                .status(statusConst.getHttpStatus())
                .body(ApiResponse.failure(statusConst, data));
    }
}
