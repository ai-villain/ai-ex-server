package com.yunsseong.ai_ex_server.post.domain;

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

class TitleTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("유효한 제목으로 Title 객체를 생성할 수 있다")
    void createTitle_WithValidTitle_ShouldCreateTitleObject() {
        // given
        String validTitle = "유효한 제목";

        // when
        Title title = new Title(validTitle);
        Set<ConstraintViolation<Title>> violations = validator.validate(title);

        // then
        assertThat(title).isNotNull();
        assertThat(title.title()).isEqualTo(validTitle);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("제목이 null이면 예외가 발생한다")
    void createTitle_WithNullTitle_ShouldThrowException() {
        // given
        Title title = new Title(null);

        // when
        Set<ConstraintViolation<Title>> violations = validator.validate(title);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<Title> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("제목을 입력해주세요");
    }

    @Test
    @DisplayName("제목이 100자를 초과하면 예외가 발생한다")
    void createTitle_WithTitleExceeding100Characters_ShouldThrowException() {
        // given
        String longTitle = "a".repeat(101);
        Title title = new Title(longTitle);

        // when
        Set<ConstraintViolation<Title>> violations = validator.validate(title);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<Title> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("제목은 100자를 넘을 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "일반적인 제목", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
    @DisplayName("제목이 1자 이상 100자 이하면 Title 객체를 생성할 수 있다")
    void createTitle_WithTitleBetween1And100Characters_ShouldCreateTitleObject(String validTitle) {
        // when
        Title title = new Title(validTitle);
        Set<ConstraintViolation<Title>> violations = validator.validate(title);

        // then
        assertThat(title).isNotNull();
        assertThat(title.title()).isEqualTo(validTitle);
        assertThat(violations).isEmpty();
    }
}
