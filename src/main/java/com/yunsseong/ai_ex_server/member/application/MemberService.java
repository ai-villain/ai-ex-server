package com.yunsseong.ai_ex_server.member.application;

import com.yunsseong.ai_ex_server.common.exception.CustomException;
import com.yunsseong.ai_ex_server.common.exception.error_code.MemberErrorCode;
import com.yunsseong.ai_ex_server.member.application.dto.CreateMemberRequest;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void createMember(CreateMemberRequest createMemberRequest) {
        Member createdMember = Member.builder()
                .nickname(createMemberRequest.nickname())
                .email(createMemberRequest.email())
                .password(createMemberRequest.password())
                .build();
        memberRepository.save(createdMember);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER));
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER));
    }

    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER));
    }

    public boolean isExistEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public boolean isExistNickname(String nickname) {
        return memberRepository.findByNickname(nickname).isPresent();
    }

    public void deleteMember(Long memberId) {
        Member foundMember = findById(memberId);
        memberRepository.delete(foundMember);
    }
}
