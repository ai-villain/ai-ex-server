package com.yunsseong.ai_ex_server.member.presentation;

import com.yunsseong.ai_ex_server.member.application.SignUpService;
import com.yunsseong.ai_ex_server.member.application.dto.SignUpRequest;
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
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request) {
        signUpService.signUp(request);
        return ResponseEntity.ok().build();
    }
}
