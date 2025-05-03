package com.yunsseong.ai_ex_server.post.application;

import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.post.application.dto.PostResponse;
import com.yunsseong.ai_ex_server.post.application.dto.UpdatePostRequest;
import com.yunsseong.ai_ex_server.post.domain.Content;
import com.yunsseong.ai_ex_server.post.domain.Post;
import com.yunsseong.ai_ex_server.post.domain.Title;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostConvertService {

    public Post createPostFromUpdateRequest(UpdatePostRequest request) {
        return Post.builder()
                .postId(request.postId())
                .title(new Title(request.title()))
                .content(new Content(request.content()))
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public PostResponse createPostResponse(Post post, Member member) {
        return PostResponse.builder()
                .postId(post.postId())
                .nickname(member.getNickname().nickname())
                .title(post.title().title())
                .content(post.content().content())
                .createdAt(post.createdAt().toString())
                .updatedAt(post.updatedAt().toString())
                .build();
    }
}
