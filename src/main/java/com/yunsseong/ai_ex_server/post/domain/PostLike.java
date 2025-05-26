package com.yunsseong.ai_ex_server.post.domain;

import com.yunsseong.ai_ex_server.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime createdAt;

    public static PostLikeBuilder builder() {
        return new PostLikeBuilder()
                .createdAt(LocalDateTime.now());
    }
}
