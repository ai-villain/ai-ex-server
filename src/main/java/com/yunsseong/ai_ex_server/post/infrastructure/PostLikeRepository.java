package com.yunsseong.ai_ex_server.post.infrastructure;

import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.post.domain.Post;
import com.yunsseong.ai_ex_server.post.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByPostAndMember(Post post, Member member);
    List<PostLike> findByPost(Post post);
}
