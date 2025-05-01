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

class EmailTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("유효한 이메일로 Email 객체를 생성할 수 있다")
    void createEmail_WithValidEmail_ShouldCreateEmailObject() {
        // given
        String validEmail = "test@example.com";

        // when
        Email email = new Email(validEmail);
        Set<ConstraintViolation<Email>> violations = validator.validate(email);

        // then
        assertThat(email).isNotNull();
        assertThat(email.email()).isEqualTo(validEmail);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("이메일이 null이면 예외가 발생한다")
    void createEmail_WithNullEmail_ShouldThrowException() {
        // given
        Email email = new Email(null);

        // when
        Set<ConstraintViolation<Email>> violations = validator.validate(email);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<Email> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("이메일을 입력해주세요");
    }

    @Test
    @DisplayName("이메일이 빈 문자열이면 예외가 발생한다")
    void createEmail_WithEmptyEmail_ShouldThrowException() {
        // given
        Email email = new Email("");

        // when
        Set<ConstraintViolation<Email>> violations = validator.validate(email);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<Email> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("이메일을 입력해주세요");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@example.com", "user.name@domain.co.kr", "email123@subdomain.example.org"})
    @DisplayName("다양한 유효한 이메일 형식으로 Email 객체를 생성할 수 있다")
    void createEmail_WithVariousValidEmails_ShouldCreateEmailObject(String validEmail) {
        // when
        Email email = new Email(validEmail);
        Set<ConstraintViolation<Email>> violations = validator.validate(email);

        // then
        assertThat(email).isNotNull();
        assertThat(email.email()).isEqualTo(validEmail);
        assertThat(violations).isEmpty();
    }
}
