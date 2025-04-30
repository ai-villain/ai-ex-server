package com.yunsseong.ai_ex_server.post.application.repository;

import com.yunsseong.ai_ex_server.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    void save(Post post);
    void update(Post post);
    Optional<Post> findById(Long postId);
    List<Post> findAll();
    void delete(Post post);
}
