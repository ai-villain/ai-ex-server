package com.yunsseong.ai_ex_server.post.presentation;

import com.yunsseong.ai_ex_server.common.dto.ApiResponse;
import com.yunsseong.ai_ex_server.common.dto.ApiResponseFactory;
import com.yunsseong.ai_ex_server.member.domain.CustomUserDetails;
import com.yunsseong.ai_ex_server.post.application.PostLikeService;
import com.yunsseong.ai_ex_server.post.application.PostService;
import com.yunsseong.ai_ex_server.post.dto.CreatePostRequest;
import com.yunsseong.ai_ex_server.post.dto.LikeCountResponse;
import com.yunsseong.ai_ex_server.post.dto.LikeResponse;
import com.yunsseong.ai_ex_server.post.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostLikeService postLikeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponse>>> getPosts() {
        return ApiResponseFactory.success(postService.findAll());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponse>> getPost(@PathVariable Long postId) {
        return ApiResponseFactory.success(postService.getPost(postId));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> writePost(@RequestBody CreatePostRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        postService.createPost(request, userDetails.getMemberId());
        return ApiResponseFactory.success();
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<ApiResponse<LikeResponse>> likePost(@PathVariable Long postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ApiResponseFactory.success(postLikeService.likePost(postId, userDetails.getMemberId()));
    }

    @DeleteMapping("/{postId}/like")
    public ResponseEntity<ApiResponse<LikeResponse>> deleteLikePost(@PathVariable Long postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ApiResponseFactory.success(postLikeService.deleteLikePost(postId, userDetails.getMemberId()));
    }

    @GetMapping("/{postId}/like")
    public ResponseEntity<ApiResponse<LikeCountResponse>> likeCount(@PathVariable Long postId) {
        return ApiResponseFactory.success(new LikeCountResponse(postLikeService.likeCount(postId)));
    }
}