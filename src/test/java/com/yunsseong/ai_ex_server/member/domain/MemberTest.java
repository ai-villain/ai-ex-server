package com.yunsseong.ai_ex_server.member.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("유효한 정보로 Member 객체를 생성할 수 있다")
    void createMember_WithValidInfo_ShouldCreateMemberObject() {
        // given
        Nickname nickname = new Nickname("testUser");
        Email email = new Email("test@example.com");
        Password password = new Password("securePassword123");

        // when
        Member member = Member.builder()
                .nickname(nickname)
                .email(email)
                .password(password)
                .build();
        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        // then
        assertThat(member).isNotNull();
        assertThat(member.nickname()).isEqualTo(nickname);
        assertThat(member.email()).isEqualTo(email);
        assertThat(member.password()).isEqualTo(password);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("닉네임이 null이면 예외가 발생한다")
    void createMember_WithNullNickname_ShouldThrowException() {
        // given
        Email email = new Email("test@example.com");
        Password password = new Password("securePassword123");

        // when
        Member member = Member.builder()
                .nickname(null)
                .email(email)
                .password(password)
                .build();
        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<Member> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("널이어서는 안됩니다");
    }

    @Test
    @DisplayName("이메일이 null이면 예외가 발생한다")
    void createMember_WithNullEmail_ShouldThrowException() {
        // given
        Nickname nickname = new Nickname("testUser");
        Password password = new Password("securePassword123");

        // when
        Member member = Member.builder()
                .nickname(nickname)
                .email(null)
                .password(password)
                .build();
        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<Member> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("널이어서는 안됩니다");
    }

    @Test
    @DisplayName("비밀번호가 null이면 예외가 발생한다")
    void createMember_WithNullPassword_ShouldThrowException() {
        // given
        Nickname nickname = new Nickname("testUser");
        Email email = new Email("test@example.com");

        // when
        Member member = Member.builder()
                .nickname(nickname)
                .email(email)
                .password(null)
                .build();
        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<Member> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("널이어서는 안됩니다");
    }

    @Test
    @DisplayName("userId는 null이어도 Member 객체를 생성할 수 있다")
    void createMember_WithNullUserId_ShouldCreateMemberObject() {
        // given
        Nickname nickname = new Nickname("testUser");
        Email email = new Email("test@example.com");
        Password password = new Password("securePassword123");

        // when
        Member member = Member.builder()
                .userId(null)
                .nickname(nickname)
                .email(email)
                .password(password)
                .build();
        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        // then
        assertThat(member).isNotNull();
        assertThat(member.userId()).isNull();
        assertThat(member.nickname()).isEqualTo(nickname);
        assertThat(member.email()).isEqualTo(email);
        assertThat(member.password()).isEqualTo(password);
        assertThat(violations).isEmpty();
    }
}
