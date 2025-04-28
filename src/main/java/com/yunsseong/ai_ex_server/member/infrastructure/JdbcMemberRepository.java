package com.yunsseong.ai_ex_server.member.infrastructure;

import com.yunsseong.ai_ex_server.member.application.repository.MemberRepository;
import com.yunsseong.ai_ex_server.member.domain.Email;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.member.domain.Nickname;
import com.yunsseong.ai_ex_server.member.domain.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Member member) {
        String sql = "insert into member (nickname, email, password) values (?, ?, ?)";
        jdbcTemplate.update(sql, member.nickname().nickname(), member.email().email(), member.password().password());
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        String sql = "select * from member where member_id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                return Member.builder()
                        .userId(rs.getLong("member_id"))
                        .nickname(new Nickname(rs.getString("nickname")))
                        .email(new Email(rs.getString("email")))
                        .password(new Password(rs.getString("password")))
                        .build();
            }, memberId));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Member member) {
        String sql = "delete from member where member_id = ?";
        jdbcTemplate.update(sql, member.userId());
    }
}
