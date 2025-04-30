package com.yunsseong.ai_ex_server.member.application;

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
                .orElseThrow(IllegalArgumentException::new);
    }

    public void deleteMember(Long memberId) {
        Member foundMember = findById(memberId);
        memberRepository.delete(foundMember);
    }
}
