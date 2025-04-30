package com.yunsseong.ai_ex_server.post.application;

import com.yunsseong.ai_ex_server.member.application.MemberService;
import com.yunsseong.ai_ex_server.member.domain.Email;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.member.domain.Nickname;
import com.yunsseong.ai_ex_server.member.domain.Password;
import com.yunsseong.ai_ex_server.post.application.dto.CreatePostRequest;
import com.yunsseong.ai_ex_server.post.application.dto.DeletePostRequest;
import com.yunsseong.ai_ex_server.post.application.dto.PostResponse;
import com.yunsseong.ai_ex_server.post.application.dto.UpdatePostRequest;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private MemberService memberService;

    @Mock
    private PostConvertService postConvertService;

    @InjectMocks
    private PostService postService;

    private Post testPost;
    private Member testMember;
    private final Long postId = 1L;
    private final Long userId = 100L;
    private final String titleStr = "테스트 제목";
    private final String contentStr = "테스트 내용";
    private final String nicknameStr = "테스트 닉네임";

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

        testMember = Member.builder()
                .userId(userId)
                .nickname(new Nickname(nicknameStr))
                .email(new Email("test@example.com"))
                .password(new Password("password123"))
                .build();
    }

    @Test
    @DisplayName("존재하는 포스트 ID로 포스트를 조회할 수 있다")
    void findPostById_WithExistingId_ShouldReturn() {
        // given
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));

        // when
        Post foundPost = postService.findById(postId);

        // then
        assertThat(foundPost).isEqualTo(testPost);
        verify(postRepository).findById(postId);
    }

    @Test
    @DisplayName("존재하지 않는 포스트 ID로 조회하면 예외가 발생한다")
    void findById_WithNonExistingId_ShouldThrowException() {
        // given
        Long nonExistingId = 999L;
        when(postRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> postService.findById(nonExistingId))
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
    void deletePost_WithNonMatchingUserId_ShouldThrowException() {
        // given
        Long differentUserId = 200L;
        DeletePostRequest request = new DeletePostRequest(postId, differentUserId);
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));

        // when & then
        assertThatThrownBy(() -> postService.deletePost(request))
                .isInstanceOf(IllegalArgumentException.class);
        verify(postRepository, never()).delete(any(Post.class));
    }

    @Test
    @DisplayName("유효한 요청으로 포스트를 업데이트할 수 있다")
    void updatePost_WithValidRequest_ShouldUpdatePost() {
        // given
        UpdatePostRequest request = new UpdatePostRequest(postId, userId, titleStr, contentStr);
        Post updatedPost = Post.builder()
                .postId(postId)
                .title(new Title(titleStr))
                .content(new Content(contentStr))
                .build();
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));
        when(postConvertService.createPostFromUpdateRequest(request)).thenReturn(updatedPost);

        // when
        postService.updatePost(request);

        // then
        verify(postRepository).update(updatedPost);
    }

    @Test
    @DisplayName("포스트 ID로 포스트 상세 정보를 조회할 수 있다")
    void getPost_WithValidId_ShouldReturnPostResponse() {
        // given
        PostResponse expectedResponse = PostResponse.builder()
                .postId(postId)
                .nickname(nicknameStr)
                .title(titleStr)
                .content(contentStr)
                .createdAt("2023-01-01T00:00:00")
                .updatedAt("2023-01-01T00:00:00")
                .build();
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));
        when(memberService.findById(userId)).thenReturn(testMember);
        when(postConvertService.createPostResponse(testPost, testMember)).thenReturn(expectedResponse);

        // when
        PostResponse response = postService.getPost(postId);

        // then
        assertThat(response).isEqualTo(expectedResponse);
        verify(postRepository).findById(postId);
        verify(memberService).findById(userId);
        verify(postConvertService).createPostResponse(testPost, testMember);
    }

    @Test
    @DisplayName("모든 포스트 목록을 조회할 수 있다")
    void findAll_ShouldReturnAllPosts() {
        // given
        List<Post> posts = List.of(testPost);
        PostResponse expectedResponse = PostResponse.builder()
                .postId(postId)
                .nickname(nicknameStr)
                .title(titleStr)
                .content(contentStr)
                .createdAt("2023-01-01T00:00:00")
                .updatedAt("2023-01-01T00:00:00")
                .build();
        when(postRepository.findAll()).thenReturn(posts);
        when(memberService.findById(userId)).thenReturn(testMember);
        when(postConvertService.createPostResponse(testPost, testMember)).thenReturn(expectedResponse);

        // when
        List<PostResponse> responses = postService.findAll();

        // then
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0)).isEqualTo(expectedResponse);
        verify(postRepository).findAll();
        verify(memberService).findById(userId);
        verify(postConvertService).createPostResponse(testPost, testMember);
    }
}
