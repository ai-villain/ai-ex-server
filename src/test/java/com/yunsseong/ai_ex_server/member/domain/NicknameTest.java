package com.yunsseong.ai_ex_server.member.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NicknameTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("유효한 닉네임으로 Nickname 객체를 생성할 수 있다")
    void createNickname_WithValidNickname_ShouldCreateNicknameObject() {
        // given
        String validNickname = "testUser";

        // when
        Nickname nickname = new Nickname(validNickname);
        Set<ConstraintViolation<Nickname>> violations = validator.validate(nickname);

        // then
        assertThat(nickname).isNotNull();
        assertThat(nickname.nickname()).isEqualTo(validNickname);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("닉네임이 null이면 예외가 발생한다")
    void createNickname_WithNullNickname_ShouldThrowException() {
        // given
        Nickname nickname = new Nickname(null);

        // when
        Set<ConstraintViolation<Nickname>> violations = validator.validate(nickname);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<Nickname> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("닉네임을 입력해주세요");
    }

    @Test
    @DisplayName("닉네임이 빈 문자열이면 예외가 발생한다")
    void createNickname_WithEmptyNickname_ShouldThrowException() {
        // given
        Nickname nickname = new Nickname("");

        // when
        Set<ConstraintViolation<Nickname>> violations = validator.validate(nickname);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<Nickname> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("닉네임을 입력해주세요");
    }

    @ParameterizedTest
    @ValueSource(strings = {"user1", "test_user", "사용자", "user123"})
    @DisplayName("다양한 유효한 닉네임으로 Nickname 객체를 생성할 수 있다")
    void createNickname_WithVariousValidNicknames_ShouldCreateNicknameObject(String validNickname) {
        // when
        Nickname nickname = new Nickname(validNickname);
        Set<ConstraintViolation<Nickname>> violations = validator.validate(nickname);

        // then
        assertThat(nickname).isNotNull();
        assertThat(nickname.nickname()).isEqualTo(validNickname);
        assertThat(violations).isEmpty();
    }
}
