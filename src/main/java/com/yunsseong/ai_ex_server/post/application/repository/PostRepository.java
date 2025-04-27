package com.yunsseong.ai_ex_server.post.application.repository;

import com.yunsseong.ai_ex_server.post.domain.Post;

import java.util.Optional;

public interface PostRepository {
    void save(Post post);
    Optional<Post> findById(Long postId);
    void delete(Post post);
}
