package com.yunsseong.ai_ex_server.member.application;

import com.yunsseong.ai_ex_server.common.exception.BusinessException;
import com.yunsseong.ai_ex_server.common.exception.error_code.MemberStatusConst;
import com.yunsseong.ai_ex_server.member.application.dto.CreateMemberRequest;
import com.yunsseong.ai_ex_server.member.application.repository.MemberRepository;
import com.yunsseong.ai_ex_server.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberConvertService memberConvertService;
    
    public void createMember(CreateMemberRequest createMemberRequest) {
        Member createdMember = memberConvertService.createMemberFromCreateRequest(createMemberRequest);
        memberRepository.save(createdMember);
    }
    
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberStatusConst.NOT_FOUND_MEMBER));
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(MemberStatusConst.NOT_FOUND_MEMBER));
    }

    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new BusinessException(MemberStatusConst.NOT_FOUND_MEMBER));
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
