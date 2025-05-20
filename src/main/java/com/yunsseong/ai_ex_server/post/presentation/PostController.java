package com.yunsseong.ai_ex_server.post.presentation;

import com.yunsseong.ai_ex_server.common.dto.ApiResponse;
import com.yunsseong.ai_ex_server.common.dto.ApiResponseFactory;
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
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponse>>> getPosts() {
        return ApiResponseFactory.success(postService.findAll());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> writePost(@RequestBody CreatePostRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        postService.createPost(request, userDetails.getMemberId());
        return ApiResponseFactory.success();
    }
}