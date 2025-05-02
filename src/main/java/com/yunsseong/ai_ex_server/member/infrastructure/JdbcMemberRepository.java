package com.yunsseong.ai_ex_server.member.infrastructure;

import com.yunsseong.ai_ex_server.member.application.repository.MemberRepository;
import com.yunsseong.ai_ex_server.member.domain.Email;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.member.domain.Nickname;
import com.yunsseong.ai_ex_server.member.domain.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
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
        jdbcTemplate.update(sql, member.getNickname().nickname(), member.getEmail().email(), member.getPassword());
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        String sql = "select * from member where member_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                    Member.builder()
                            .memberId(rs.getLong("member_id"))
                            .nickname(new Nickname(rs.getString("nickname")))
                            .email(new Email(rs.getString("email")))
                            .password(new Password(rs.getString("password")))
                            .build(), memberId));
        } catch (DataAccessException ex) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Member> findByEmail(String email) {
        String sql = "select * from member where email = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                    Member.builder()
                            .memberId(rs.getLong("member_id"))
                            .nickname(new Nickname(rs.getString("nickname")))
                            .email(new Email(rs.getString("email")))
                            .password(new Password(rs.getString("password")))
                            .build(), email));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        try {
            String sql = "select * from member where nickname = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                    Member.builder()
                            .memberId(rs.getLong("member_id"))
                            .nickname(new Nickname(rs.getString("nickname")))
                            .email(new Email(rs.getString("email")))
                            .password(new Password(rs.getString("password")))
                            .build(), nickname));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Member member) {
        String sql = "delete from member where member_id = ?";
        jdbcTemplate.update(sql, member.getMemberId());
    }
}
