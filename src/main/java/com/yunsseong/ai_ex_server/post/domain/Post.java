package com.yunsseong.ai_ex_server.post.domain;

import com.yunsseong.ai_ex_server.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 500)
    private String content;

    private Long likeCount = 0L;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public boolean isCreatedBy(Long memberId) {
        return this.member.equals(memberId);
    }

    public static PostBuilder builder() {
        return new PostBuilder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now());
    }

    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void decreaseLikeCount() {
        this.likeCount--;
    }
}
