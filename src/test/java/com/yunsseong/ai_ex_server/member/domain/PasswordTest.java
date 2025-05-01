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

class PasswordTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("유효한 비밀번호로 Password 객체를 생성할 수 있다")
    void createPassword_WithValidPassword_ShouldCreatePasswordObject() {
        // given
        String validPassword = "securePassword123";

        // when
        Password password = new Password(validPassword);
        Set<ConstraintViolation<Password>> violations = validator.validate(password);

        // then
        assertThat(password).isNotNull();
        assertThat(password.password()).isEqualTo(validPassword);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("비밀번호가 null이면 예외가 발생한다")
    void createPassword_WithNullPassword_ShouldThrowException() {
        // given
        Password password = new Password(null);

        // when
        Set<ConstraintViolation<Password>> violations = validator.validate(password);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<Password> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("닉네임을 입력해주세요");
    }

    @Test
    @DisplayName("비밀번호가 빈 문자열이면 예외가 발생한다")
    void createPassword_WithEmptyPassword_ShouldThrowException() {
        // given
        Password password = new Password("");

        // when
        Set<ConstraintViolation<Password>> violations = validator.validate(password);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<Password> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("닉네임을 입력해주세요");
    }

    @ParameterizedTest
    @ValueSource(strings = {"password123", "P@ssw0rd", "SecurePassword", "12345678"})
    @DisplayName("다양한 유효한 비밀번호로 Password 객체를 생성할 수 있다")
    void createPassword_WithVariousValidPasswords_ShouldCreatePasswordObject(String validPassword) {
        // when
        Password password = new Password(validPassword);
        Set<ConstraintViolation<Password>> violations = validator.validate(password);

        // then
        assertThat(password).isNotNull();
        assertThat(password.password()).isEqualTo(validPassword);
        assertThat(violations).isEmpty();
    }
}
