package com.yunsseong.ai_ex_server.post.application;

import com.yunsseong.ai_ex_server.member.application.MemberService;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.post.domain.Post;
import com.yunsseong.ai_ex_server.post.domain.PostLike;
import com.yunsseong.ai_ex_server.post.dto.LikeResponse;
import com.yunsseong.ai_ex_server.post.infrastructure.PostLikeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostLikeServiceTest {

    @Mock
    PostLikeRepository postLikeRepository;

    @Mock
    PostService postService;

    @Mock
    MemberService memberService;

    @InjectMocks
    PostLikeService postLikeService;

    @Test
    void 게시글에_좋아요를_누르면_좋아요_수가_1_증가한다() {
        // given
        Long postId = 1L;
        Long memberId = 2L;
        Post post = Post.builder().id(postId).likeCount(0L).build();
        Member member = Member.builder().id(memberId).build();
        PostLike postLike = PostLike.builder().post(post).member(member).build();

        when(postService.findById(postId)).thenReturn(post);
        when(memberService.findById(memberId)).thenReturn(member);
        when(postLikeRepository.save(any(PostLike.class))).thenReturn(postLike);

        // when
        LikeResponse likeResponse = postLikeService.likePost(postId, memberId);

        // then
        assertThat(likeResponse.isLiked()).isTrue();
        assertThat(likeResponse.likeCount()).isEqualTo(1L);
    }

    @Test
    void 좋아요를_누른_게시글에_다시_좋아요를_누르면_좋아요_수가_1_감소한다() {
        // given
        Long postId = 1L;
        Long memberId = 2L;
        Post post = Post.builder().id(postId).likeCount(1L).build();
        Member member = Member.builder().id(memberId).build();
        PostLike postLike = PostLike.builder().post(post).member(member).build();

        when(postService.findById(postId)).thenReturn(post);
        when(postLikeRepository.findByPostIdAndMemberId(postId, memberId)).thenReturn(Optional.of(postLike));
        doNothing().when(postLikeRepository).delete(any(PostLike.class));

        // when
        LikeResponse likeResponse = postLikeService.deleteLikePost(postId, memberId);

        // then
        assertThat(likeResponse.isLiked()).isFalse();
        assertThat(likeResponse.likeCount()).isEqualTo(0L);
    }

    @Test
    void 게시글에_사용자가_좋아요를_남겼다면_isLiked에서_참을_반환한다() {
        // given
        Long postId = 1L;
        Long memberId = 2L;
        Post post = Post.builder().id(postId).likeCount(1L).build();
        Member member = Member.builder().id(memberId).build();
        PostLike postLike = PostLike.builder().post(post).member(member).build();

        // when
        when(postLikeRepository.findByPostIdAndMemberId(postId, memberId)).thenReturn(Optional.of(postLike));
        boolean likeResult = postLikeService.isLiked(postId, memberId);

        // then
        assertThat(likeResult).isTrue();
    }

    @Test
    void 게시글에_사용자가_좋아요를_남기지_않았다면_isLiked에서_거짓을_반환한다() {
        // given
        Long postId = 1L;
        Long memberId = 2L;

        // when
        when(postLikeRepository.findByPostIdAndMemberId(postId, memberId)).thenReturn(Optional.empty());
        boolean likeResult = postLikeService.isLiked(postId, memberId);

        // then
        assertThat(likeResult).isFalse();
    }

    @Test
    void 게시글의_좋아요를_조회할_수_있다(){
        // given
        Long postId = 1L;
        Long memberId = 2L;
        Long likeCount = 10L;
        Post post = Post.builder().id(postId).likeCount(likeCount).build();

        // when
        when(postService.findById(postId)).thenReturn(post);
        Long likeCountResult = postLikeService.likeCount(postId);

        // then
        assertThat(likeCountResult).isEqualTo(likeCount);
    }
}