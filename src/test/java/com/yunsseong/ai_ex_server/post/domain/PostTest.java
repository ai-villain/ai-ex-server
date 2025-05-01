package com.yunsseong.ai_ex_server.post.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

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
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        // then
        assertThat(post).isNotNull();
        assertThat(post.postId()).isEqualTo(postId);
        assertThat(post.title()).isEqualTo(title);
        assertThat(post.content()).isEqualTo(content);
        assertThat(post.userId()).isEqualTo(userId);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("title이 null이면 예외가 발생한다")
    void createPost_WithNullTitle_ShouldThrowException() {
        // given
        Long postId = 1L;
        Title title = null;
        Content content = new Content("내용");
        Long userId = 100L;

        // when
        Post post = Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .userId(userId)
                .build();
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<Post> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("title");
    }

    @Test
    @DisplayName("content가 null이면 예외가 발생한다")
    void createPost_WithNullContent_ShouldThrowException() {
        // given
        Long postId = 1L;
        Title title = new Title("제목");
        Content content = null;
        Long userId = 100L;

        // when
        Post post = Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .userId(userId)
                .build();
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<Post> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("content");
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
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        // then
        assertThat(post).isNotNull();
        assertThat(post.postId()).isNull();
        assertThat(post.title()).isEqualTo(title);
        assertThat(post.content()).isEqualTo(content);
        assertThat(post.userId()).isEqualTo(userId);
        assertThat(violations).isEmpty();
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
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        // then
        assertThat(post).isNotNull();
        assertThat(post.postId()).isEqualTo(postId);
        assertThat(post.title()).isEqualTo(title);
        assertThat(post.content()).isEqualTo(content);
        assertThat(post.userId()).isNull();
        assertThat(violations).isEmpty();
    }
}
