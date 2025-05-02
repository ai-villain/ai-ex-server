package com.yunsseong.ai_ex_server.post.presentation;

import com.yunsseong.ai_ex_server.member.application.MemberService;
import com.yunsseong.ai_ex_server.member.domain.Member;
import com.yunsseong.ai_ex_server.post.application.PostService;
import com.yunsseong.ai_ex_server.post.application.dto.CreatePostRequest;
import com.yunsseong.ai_ex_server.post.application.dto.PostResponse;
import com.yunsseong.ai_ex_server.post.presentation.dto.WritePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> writePost(@RequestBody WritePostRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        Member foundMember = memberService.findByEmail(userDetails.getUsername());
        System.out.println("foundMember.getMemberId() = " + foundMember.getMemberId());
        CreatePostRequest createPostRequest = CreatePostRequest.builder()
                .memberId(foundMember.getMemberId())
                .title(request.title())
                .content(request.content())
                .build();
        postService.createPost(createPostRequest);
        return ResponseEntity.ok().build();
    }
}