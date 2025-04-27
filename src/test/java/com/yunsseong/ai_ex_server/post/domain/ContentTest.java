package com.yunsseong.ai_ex_server.post.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ContentTest {

    @Test
    @DisplayName("유효한 내용으로 Content 객체를 생성할 수 있다")
    void createContent_WithValidContent_ShouldCreateContentObject() {
        // given
        String validContent = "유효한 내용";

        // when
        Content content = new Content(validContent);

        // then
        assertThat(content).isNotNull();
        assertThat(content.content()).isEqualTo(validContent);
    }

    @Test
    @DisplayName("내용이 null이면 예외가 발생한다")
    void createContent_WithNullContent_ShouldThrowException() {
        // given
        String nullContent = null;

        // when & then
        assertThatThrownBy(() -> new Content(nullContent))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("내용을 입력해주세요");
    }

    @Test
    @DisplayName("내용이 500자를 초과하면 예외가 발생한다")
    void createContent_WithContentExceeding500Characters_ShouldThrowException() {
        // given
        String longContent = "a".repeat(501);

        // when & then
        assertThatThrownBy(() -> new Content(longContent))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("내용은 500자를 넘을 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "일반적인 내용"})
    @DisplayName("내용이 1자 이상 일반 길이면 Content 객체를 생성할 수 있다")
    void createContent_WithContentBetween1AndNormalLength_ShouldCreateContentObject(String validContent) {
        // when
        Content content = new Content(validContent);

        // then
        assertThat(content).isNotNull();
        assertThat(content.content()).isEqualTo(validContent);
    }

    @Test
    @DisplayName("내용이 정확히 500자이면 Content 객체를 생성할 수 있다")
    void createContent_WithContentExactly500Characters_ShouldCreateContentObject() {
        // given
        String maxLengthContent = "a".repeat(500);

        // when
        Content content = new Content(maxLengthContent);

        // then
        assertThat(content).isNotNull();
        assertThat(content.content()).isEqualTo(maxLengthContent);
    }
}
