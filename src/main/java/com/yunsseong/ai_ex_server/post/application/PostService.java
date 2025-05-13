package com.yunsseong.ai_ex_server.post.application;

import com.yunsseong.ai_ex_server.common.exception.BusinessException;
import com.yunsseong.ai_ex_server.common.exception.error_code.PostStatusConst;
import com.yunsseong.ai_ex_server.member.application.MemberService;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.post.application.dto.CreatePostRequest;
import com.yunsseong.ai_ex_server.post.application.dto.DeletePostRequest;
import com.yunsseong.ai_ex_server.post.application.dto.PostResponse;
import com.yunsseong.ai_ex_server.post.application.dto.UpdatePostRequest;
import com.yunsseong.ai_ex_server.post.domain.Post;
import com.yunsseong.ai_ex_server.post.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final PostConvertService postConvertService;

    public void createPost(CreatePostRequest request) {
        Member foundMember = memberService.findById(request.memberId());
        Post createdPost = Post.builder()
                .member(foundMember)
                .title(request.title())
                .content(request.content())
                .build();
        foundMember.addPost(createdPost);
        postRepository.save(createdPost);
    }

    public void updatePost(UpdatePostRequest request) {
        if (!findById(request.postId()).isCreatedBy(request.memberId()))
            throw new BusinessException(PostStatusConst.WRITER_MISMATCH);
        Post updatedPost = postConvertService.createPostFromUpdateRequest(request);
        postRepository.save(updatedPost);
    }

    public List<PostResponse> findAll() {
        return postRepository.findAll().stream()
                .map(postConvertService::createPostResponse).toList();
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(PostStatusConst.NOT_FOUND_POST));
    }

    public PostResponse getPost(Long postId) {
        Post foundPost = findById(postId);
        return postConvertService.createPostResponse(foundPost);
    }

    public void deletePost(DeletePostRequest request) {
        Post foundPost = findById(request.postId());
        if (!foundPost.isCreatedBy(request.memberId()))
            throw new BusinessException(PostStatusConst.WRITER_MISMATCH);
        postRepository.delete(foundPost);
    }
}
