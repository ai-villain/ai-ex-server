package com.yunsseong.ai_ex_server.post.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PostTest {

    @Test
    @DisplayName("유효한 값으로 Post 객체를 생성할 수 있다")
    void createPost_WithValidValues_ShouldCreatePostObject() {
        // given
        Long postId = 1L;
        Title title = new Title("제목");
        Content content = new Content("내용");
        Long userId = 100L;

        // when
        Post post = Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .userId(userId)
                .build();

        // then
        assertThat(post).isNotNull();
        assertThat(post.postId()).isEqualTo(postId);
        assertThat(post.title()).isEqualTo(title);
        assertThat(post.content()).isEqualTo(content);
        assertThat(post.userId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("title이 null이면 예외가 발생한다")
    void createPost_WithNullTitle_ShouldThrowException() {
        // given
        Long postId = 1L;
        Title title = null;
        Content content = new Content("내용");
        Long userId = 100L;

        // when & then
        assertThatThrownBy(() -> Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .userId(userId)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("content가 null이면 예외가 발생한다")
    void createPost_WithNullContent_ShouldThrowException() {
        // given
        Long postId = 1L;
        Title title = new Title("제목");
        Content content = null;
        Long userId = 100L;

        // when & then
        assertThatThrownBy(() -> Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .userId(userId)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("postId가 null이어도 Post 객체를 생성할 수 있다")
    void createPost_WithNullPostId_ShouldCreatePostObject() {
        // given
        Long postId = null;
        Title title = new Title("제목");
        Content content = new Content("내용");
        Long userId = 100L;

        // when
        Post post = Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .userId(userId)
                .build();

        // then
        assertThat(post).isNotNull();
        assertThat(post.postId()).isNull();
        assertThat(post.title()).isEqualTo(title);
        assertThat(post.content()).isEqualTo(content);
        assertThat(post.userId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("userId가 null이어도 Post 객체를 생성할 수 있다")
    void createPost_WithNullUserId_ShouldCreatePostObject() {
        // given
        Long postId = 1L;
        Title title = new Title("제목");
        Content content = new Content("내용");
        Long userId = null;

        // when
        Post post = Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .userId(userId)
                .build();

        // then
        assertThat(post).isNotNull();
        assertThat(post.postId()).isEqualTo(postId);
        assertThat(post.title()).isEqualTo(title);
        assertThat(post.content()).isEqualTo(content);
        assertThat(post.userId()).isNull();
    }
}
