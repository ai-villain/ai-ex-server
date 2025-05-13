package com.yunsseong.ai_ex_server.common.exception;

import com.yunsseong.ai_ex_server.common.dto.ApiResponse;
import com.yunsseong.ai_ex_server.common.dto.ApiResponseFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.yunsseong.ai_ex_server.common.exception.CommonStatusCode.INVALID_INPUT_VALUE;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> businessExceptionHandler(BusinessException ex) {
        StatusConst statusConst = ex.getStatusConst();
        return ApiResponseFactory.bizException(statusConst);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ApiResponse<Void>> systemExceptionHandler(SystemException ex) {
        log.info("SystemException : {}", ex.getMessage());
        StatusConst statusConst = ex.getStatusConst();
        return ApiResponseFactory.sysException(statusConst);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ApiResponseFactory.bizException(INVALID_INPUT_VALUE, errors);
    }
}