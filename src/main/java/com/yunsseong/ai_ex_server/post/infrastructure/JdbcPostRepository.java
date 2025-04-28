package com.yunsseong.ai_ex_server.post.infrastructure;

import com.yunsseong.ai_ex_server.post.application.repository.PostRepository;
import com.yunsseong.ai_ex_server.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcPostRepository implements PostRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Post post) {
        String sql = "insert into post (title, content, user_id, created_at) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.title(), post.content(), post.userId(), post.createdAt());
    }

    @Override
    public Optional<Post> findById(Long postId) {
        String sql = "select * from post where post_id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new DataClassRowMapper<>(Post.class), postId));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Post post) {
        String sql = "delete from post where post_id = ?";
        jdbcTemplate.update(sql, post.postId());
    }
}
