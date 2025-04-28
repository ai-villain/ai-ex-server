package com.yunsseong.ai_ex_server.post.application;

import com.yunsseong.ai_ex_server.post.application.dto.CreatePostRequest;
import com.yunsseong.ai_ex_server.post.application.dto.DeletePostRequest;
import com.yunsseong.ai_ex_server.post.application.repository.PostRepository;
import com.yunsseong.ai_ex_server.post.domain.Content;
import com.yunsseong.ai_ex_server.post.domain.Post;
import com.yunsseong.ai_ex_server.post.domain.Title;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private Post testPost;
    private final Long postId = 1L;
    private final Long userId = 100L;
    private final String titleStr = "테스트 제목";
    private final String contentStr = "테스트 내용";

    @BeforeEach
    void setUp() {
        Title title = new Title(titleStr);
        Content content = new Content(contentStr);
        testPost = Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }

    @Test
    @DisplayName("존재하는 포스트 ID로 포스트를 조회할 수 있다")
    void findPostById_WithExistingId_ShouldReturnPost() {
        // given
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));

        // when
        Post foundPost = postService.findPostById(postId);

        // then
        assertThat(foundPost).isEqualTo(testPost);
        verify(postRepository).findById(postId);
    }

    @Test
    @DisplayName("존재하지 않는 포스트 ID로 조회하면 예외가 발생한다")
    void findPostById_WithNonExistingId_ShouldThrowException() {
        // given
        Long nonExistingId = 999L;
        when(postRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> postService.findPostById(nonExistingId))
                .isInstanceOf(IllegalArgumentException.class);
        verify(postRepository).findById(nonExistingId);
    }

    @Test
    @DisplayName("유효한 요청으로 포스트를 생성할 수 있다")
    void createPost_WithValidRequest_ShouldCreatePost() {
        // given
        CreatePostRequest request = new CreatePostRequest(userId, titleStr, contentStr);
        
        // when
        postService.createPost(request);

        // then
        verify(postRepository).save(any(Post.class));
    }

    @Test
    @DisplayName("포스트 작성자와 일치하는 사용자 ID로 포스트를 삭제할 수 있다")
    void deletePost_WithMatchingUserId_ShouldDeletePost() {
        // given
        DeletePostRequest request = new DeletePostRequest(postId, userId);
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));

        // when
        postService.deletePost(request);

        // then
        verify(postRepository).delete(testPost);
    }

    @Test
    @DisplayName("포스트 작성자와 일치하지 않는 사용자 ID로는 포스트를 삭제할 수 없다")
    void deletePost_WithNonMatchingUserId_ShouldNotDeletePost() {
        // given
        Long differentUserId = 200L;
        DeletePostRequest request = new DeletePostRequest(postId, differentUserId);
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));

        // when
        postService.deletePost(request);

        // then
        verify(postRepository, never()).delete(any(Post.class));
    }
}