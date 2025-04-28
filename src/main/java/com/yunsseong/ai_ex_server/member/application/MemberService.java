package com.yunsseong.ai_ex_server.member.application;

import com.yunsseong.ai_ex_server.member.application.repository.MemberRepository;
import com.yunsseong.ai_ex_server.member.domain.Email;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.member.domain.Nickname;
import com.yunsseong.ai_ex_server.member.domain.Password;
import com.yunsseong.ai_ex_server.member.application.dto.CreateMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    
    public void createMember(CreateMemberRequest createMemberRequest) {
        Member createdMember = Member.builder()
                .nickname(new Nickname(createMemberRequest.nickname()))
                .email(new Email(createMemberRequest.email()))
                .password(new Password(createMemberRequest.password()))
                .build();
        memberRepository.save(createdMember);
    }
    
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(IllegalArgumentException::new);
    }

    public void deleteMember(Long memberId) {
        Member foundMember = findMemberById(memberId);
        memberRepository.delete(foundMember);
    }
}
