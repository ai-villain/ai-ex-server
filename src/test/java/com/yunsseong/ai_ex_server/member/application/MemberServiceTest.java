package com.yunsseong.ai_ex_server.member.application;

import com.yunsseong.ai_ex_server.member.application.dto.CreateMemberRequest;
import com.yunsseong.ai_ex_server.member.application.repository.MemberRepository;
import com.yunsseong.ai_ex_server.member.domain.Email;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.member.domain.Nickname;
import com.yunsseong.ai_ex_server.member.domain.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberConvertService memberConvertService;

    @InjectMocks
    private MemberService memberService;

    private Member testMember;
    private final Long userId = 1L;
    private final String nicknameStr = "테스트닉네임";
    private final String emailStr = "test@example.com";
    private final String passwordStr = "password123";

    @BeforeEach
    void setUp() {
        Nickname nickname = new Nickname(nicknameStr);
        Email email = new Email(emailStr);
        Password password = new Password(passwordStr);
        testMember = Member.builder()
                .userId(userId)
                .nickname(nickname)
                .email(email)
                .password(password)
                .build();
    }

    @Test
    @DisplayName("유효한 요청으로 회원을 생성할 수 있다")
    void createMember_WithValidRequest_ShouldCreateMember() {
        // given
        CreateMemberRequest request = new CreateMemberRequest(nicknameStr, emailStr, passwordStr);
        Member expectedMember = Member.builder()
                .nickname(new Nickname(nicknameStr))
                .email(new Email(emailStr))
                .password(new Password(passwordStr))
                .build();
        when(memberConvertService.createMemberFromCreateRequest(request)).thenReturn(expectedMember);

        // when
        memberService.createMember(request);

        // then
        verify(memberConvertService).createMemberFromCreateRequest(request);
        verify(memberRepository).save(expectedMember);
    }

    @Test
    @DisplayName("존재하는 회원 ID로 회원을 조회할 수 있다")
    void findMemberById_WithExistingId_ShouldReturn() {
        // given
        when(memberRepository.findById(userId)).thenReturn(Optional.of(testMember));

        // when
        Member foundMember = memberService.findById(userId);

        // then
        assertThat(foundMember).isEqualTo(testMember);
        verify(memberRepository).findById(userId);
    }

    @Test
    @DisplayName("존재하지 않는 회원 ID로 조회하면 예외가 발생한다")
    void findById_WithNonExistingId_ShouldThrowException() {
        // given
        Long nonExistingId = 999L;
        when(memberRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> memberService.findById(nonExistingId))
                .isInstanceOf(IllegalArgumentException.class);
        verify(memberRepository).findById(nonExistingId);
    }

    @Test
    @DisplayName("존재하는 회원 ID로 회원을 삭제할 수 있다")
    void deleteMember_WithExistingId_ShouldDeleteMember() {
        // given
        when(memberRepository.findById(userId)).thenReturn(Optional.of(testMember));

        // when
        memberService.deleteMember(userId);

        // then
        verify(memberRepository).findById(userId);
        verify(memberRepository).delete(testMember);
    }
}
