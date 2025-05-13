package com.yunsseong.ai_ex_server.auth.presentation;

import com.yunsseong.ai_ex_server.auth.service.SignUpService;
import com.yunsseong.ai_ex_server.auth.dto.SignUpRequest;
import com.yunsseong.ai_ex_server.common.dto.ApiResponse;
import com.yunsseong.ai_ex_server.common.dto.ApiResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sign-up")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> signUp(@RequestBody SignUpRequest request) {
        signUpService.signUp(request);
        return ApiResponseFactory.success();
    }
}
