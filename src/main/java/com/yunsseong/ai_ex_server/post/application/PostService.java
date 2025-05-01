package com.yunsseong.ai_ex_server.post.application;

import com.yunsseong.ai_ex_server.common.exception.CustomException;
import com.yunsseong.ai_ex_server.common.exception.error_code.PostErrorCode;
import com.yunsseong.ai_ex_server.member.application.MemberService;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.post.application.dto.CreatePostRequest;
import com.yunsseong.ai_ex_server.post.application.dto.DeletePostRequest;
import com.yunsseong.ai_ex_server.post.application.dto.PostResponse;
import com.yunsseong.ai_ex_server.post.application.dto.UpdatePostRequest;
import com.yunsseong.ai_ex_server.post.application.repository.PostRepository;
import com.yunsseong.ai_ex_server.post.domain.Content;
import com.yunsseong.ai_ex_server.post.domain.Post;
import com.yunsseong.ai_ex_server.post.domain.Title;
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
        Post createdPost = Post.builder()
                .title(new Title(request.title()))
                .content(new Content(request.content()))
                .build();
        postRepository.save(createdPost);
    }

    public void updatePost(UpdatePostRequest request) {
        if (!findById(request.postId()).isCreatedBy(request.userId()))
            throw new CustomException(PostErrorCode.WRITER_MISMATCH);
        Post updatedPost = postConvertService.createPostFromUpdateRequest(request);
        postRepository.update(updatedPost);
    }

    public List<PostResponse> findAll() {
        return postRepository.findAll().stream()
                .map(post -> {
                    Member foundMember = memberService.findById(post.userId());
                    return postConvertService.createPostResponse(post, foundMember);
                }).toList();
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(PostErrorCode.NOT_FOUND_POST));
    }

    public PostResponse getPost(Long postId) {
        Post foundPost = findById(postId);
        Member foundMember = memberService.findById(foundPost.userId());
        return postConvertService.createPostResponse(foundPost, foundMember);
    }

    public void deletePost(DeletePostRequest request) {
        Post foundPost = findById(request.postId());
        if (!foundPost.isCreatedBy(request.userId()))
            throw new CustomException(PostErrorCode.WRITER_MISMATCH);
        postRepository.delete(foundPost);
    }
}
