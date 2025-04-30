package com.yunsseong.ai_ex_server.member.application;

import com.yunsseong.ai_ex_server.member.application.dto.CreateMemberRequest;
import com.yunsseong.ai_ex_server.member.domain.Email;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.member.domain.Nickname;
import com.yunsseong.ai_ex_server.member.domain.Password;
import org.springframework.stereotype.Service;

@Service
public class MemberConvertService {

    public Member createMemberFromCreateRequest(CreateMemberRequest request) {
        return Member.builder()
                .nickname(new Nickname(request.nickname()))
                .email(new Email(request.email()))
                .password(new Password(request.password()))
                .build();
    }
}
