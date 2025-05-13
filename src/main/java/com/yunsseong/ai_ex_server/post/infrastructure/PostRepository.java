package com.yunsseong.ai_ex_server.post.infrastructure;

import com.yunsseong.ai_ex_server.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
