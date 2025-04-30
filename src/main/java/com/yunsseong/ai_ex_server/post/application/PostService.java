package com.yunsseong.ai_ex_server.post.application;

import com.yunsseong.ai_ex_server.post.application.dto.CreatePostRequest;
import com.yunsseong.ai_ex_server.post.application.dto.DeletePostRequest;
import com.yunsseong.ai_ex_server.post.application.repository.PostRepository;
import com.yunsseong.ai_ex_server.post.domain.Content;
import com.yunsseong.ai_ex_server.post.domain.Post;
import com.yunsseong.ai_ex_server.post.domain.Title;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(IllegalArgumentException::new);
    }

    public void createPost(CreatePostRequest request) {
        Post createdPost = Post.builder()
                .title(new Title(request.title()))
                .content(new Content(request.content()))
                .build();
        postRepository.save(createdPost);
    }

    public void deletePost(DeletePostRequest request) {
        Post foundPost = findById(request.postId());
        if (!foundPost.isCreatedBy(request.userId()))
            throw new IllegalArgumentException();
        postRepository.delete(foundPost);
    }
}
