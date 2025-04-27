package com.yunsseong.ai_ex_server.post.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TitleTest {

    @Test
    @DisplayName("유효한 제목으로 Title 객체를 생성할 수 있다")
    void createTitle_WithValidTitle_ShouldCreateTitleObject() {
        // given
        String validTitle = "유효한 제목";

        // when
        Title title = new Title(validTitle);

        // then
        assertThat(title).isNotNull();
        assertThat(title.title()).isEqualTo(validTitle);
    }

    @Test
    @DisplayName("제목이 null이면 예외가 발생한다")
    void createTitle_WithNullTitle_ShouldThrowException() {
        // given
        String nullTitle = null;

        // when & then
        assertThatThrownBy(() -> new Title(nullTitle))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제목을 입력해주세요");
    }

    @Test
    @DisplayName("제목이 100자를 초과하면 예외가 발생한다")
    void createTitle_WithTitleExceeding100Characters_ShouldThrowException() {
        // given
        String longTitle = "a".repeat(101);

        // when & then
        assertThatThrownBy(() -> new Title(longTitle))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제목은 100자를 넘을 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "일반적인 제목", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
    @DisplayName("제목이 1자 이상 100자 이하면 Title 객체를 생성할 수 있다")
    void createTitle_WithTitleBetween1And100Characters_ShouldCreateTitleObject(String validTitle) {
        // when
        Title title = new Title(validTitle);

        // then
        assertThat(title).isNotNull();
        assertThat(title.title()).isEqualTo(validTitle);
    }
}
