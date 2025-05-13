package com.yunsseong.ai_ex_server.post.application;

import com.yunsseong.ai_ex_server.post.application.dto.PostResponse;
import com.yunsseong.ai_ex_server.post.application.dto.UpdatePostRequest;
import com.yunsseong.ai_ex_server.post.domain.Post;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostConvertService {

    public Post createPostFromUpdateRequest(UpdatePostRequest request) {
        return Post.builder()
                .id(request.postId())
                .title(request.title())
                .content(request.content())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public PostResponse createPostResponse(Post post) {
        return PostResponse.builder()
                .postId(post.getId())
                .nickname(post.getMember().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt().toString())
                .updatedAt(post.getUpdatedAt().toString())
                .build();
    }
}
