package com.yunsseong.ai_ex_server.post.application;

import com.yunsseong.ai_ex_server.member.application.MemberService;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.post.domain.Post;
import com.yunsseong.ai_ex_server.post.domain.PostLike;
import com.yunsseong.ai_ex_server.post.infrastructure.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostService postService;
    private final MemberService memberService;

    @Transactional
    public boolean likePost(Long postId, Long memberId) {
        Post foundPost = postService.findById(postId);
        Member foundMember = memberService.findById(memberId);
        Optional<PostLike> foundLike = postLikeRepository.findByPostAndMember(foundPost, foundMember);

        if (foundLike.isPresent()) {
            postLikeRepository.delete(foundLike.get());
            foundPost.decreaseLikeCount();
            return false;
        }

        postLikeRepository.save(
                PostLike.builder()
                .post(foundPost)
                .member(foundMember)
                .build()
        );
        foundPost.increaseLikeCount();
        return true;
    }

    @Transactional
    public Long likeCount(Long postId) {
        Post foundPost = postService.findById(postId);
        return foundPost.getLikeCount();
    }
}
