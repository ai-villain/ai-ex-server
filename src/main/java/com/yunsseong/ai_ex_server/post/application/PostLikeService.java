package com.yunsseong.ai_ex_server.post.application;

import com.yunsseong.ai_ex_server.common.exception.BusinessException;
import com.yunsseong.ai_ex_server.member.application.MemberService;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.post.domain.Post;
import com.yunsseong.ai_ex_server.post.domain.PostLike;
import com.yunsseong.ai_ex_server.post.dto.LikeResponse;
import com.yunsseong.ai_ex_server.post.exception.PostStatusConst;
import com.yunsseong.ai_ex_server.post.infrastructure.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostService postService;
    private final MemberService memberService;


    @Transactional
    public LikeResponse likePost(Long postId, Long memberId) {
        Post foundPost = postService.findById(postId);
        Member foundMember = memberService.findById(memberId);

        postLikeRepository.save(
                PostLike.builder()
                .post(foundPost)
                .member(foundMember)
                .build()
        );
        foundPost.increaseLikeCount();
        return LikeResponse.builder()
                .isLiked(true)
                .likeCount(foundPost.getLikeCount())
                .build();
    }

    @Transactional
    public LikeResponse deleteLikePost(Long postId, Long memberId) {
        Post foundPost = postService.findById(postId);
        PostLike postLike = findPostLike(postId, memberId);

        foundPost.decreaseLikeCount();
        postLikeRepository.delete(postLike);
        return LikeResponse.builder()
                .isLiked(false)
                .likeCount(foundPost.getLikeCount())
                .build();
    }

    public PostLike findPostLike(Long postId, Long memberId) {
        return postLikeRepository.findByPostIdAndMemberId(postId, memberId)
                .orElseThrow(() -> new BusinessException(PostStatusConst.NOT_FOUND_POST));
    }

    public boolean isLiked(Long postId, Long memberId) {
        return postLikeRepository.findByPostIdAndMemberId(postId, memberId).isPresent();
    }

    public Long likeCount(Long postId) {
        Post foundPost = postService.findById(postId);
        return foundPost.getLikeCount();
    }
}
