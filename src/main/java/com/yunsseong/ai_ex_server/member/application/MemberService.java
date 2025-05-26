package com.yunsseong.ai_ex_server.member.application;

import com.yunsseong.ai_ex_server.common.exception.BusinessException;
import com.yunsseong.ai_ex_server.member.application.dto.CreateMemberRequest;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.member.exception.MemberStatusConst;
import com.yunsseong.ai_ex_server.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(CreateMemberRequest createMemberRequest) {
        Member createdMember = Member.builder()
                .nickname(createMemberRequest.nickname())
                .email(createMemberRequest.email())
                .password(createMemberRequest.password())
                .build();
        memberRepository.save(createdMember);
    }

    @Transactional(readOnly = true)
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberStatusConst.NOT_FOUND_MEMBER));
    }

    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(MemberStatusConst.NOT_FOUND_MEMBER));
    }

    @Transactional(readOnly = true)
    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new BusinessException(MemberStatusConst.NOT_FOUND_MEMBER));
    }

    @Transactional(readOnly = true)
    public boolean isExistEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    @Transactional(readOnly = true)
    public boolean isExistNickname(String nickname) {
        return memberRepository.findByNickname(nickname).isPresent();
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member foundMember = findById(memberId);
        memberRepository.delete(foundMember);
    }
}
