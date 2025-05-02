package com.yunsseong.ai_ex_server.member.application.repository;

import com.yunsseong.ai_ex_server.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findById(Long memberId);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
    void delete(Member member);
}
