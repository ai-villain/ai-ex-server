package com.yunsseong.ai_ex_server.member.application;

import com.yunsseong.ai_ex_server.common.exception.BusinessException;
import com.yunsseong.ai_ex_server.common.exception.error_code.SignUpStatusConst;
import com.yunsseong.ai_ex_server.member.application.dto.CreateMemberRequest;
import com.yunsseong.ai_ex_server.member.application.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public void singUp(SignUpRequest request) {
        if (memberService.isExistEmail(request.email()))
            throw new BusinessException(SignUpStatusConst.ALREADY_EXIST_EMAIL);
        if (memberService.isExistNickname(request.nickname()))
            throw new BusinessException(SignUpStatusConst.ALREADY_EXIST_NICKNAME);
        if (!request.password1().equals(request.password2()))
            throw new BusinessException(SignUpStatusConst.PASSWORDS_MISMATCH);

        CreateMemberRequest createMemberRequest = CreateMemberRequest.builder()
                .nickname(request.nickname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password1()))
                .build();
        memberService.createMember(createMemberRequest);
    }
}
