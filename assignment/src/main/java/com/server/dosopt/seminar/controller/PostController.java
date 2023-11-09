package com.server.dosopt.seminar.controller;

import static com.server.dosopt.seminar.enums.SuccessMessage.*;

import com.server.dosopt.seminar.common.response.ApiResponse;
import com.server.dosopt.seminar.common.response.ApiResponse.CreatedApiResponse;
import com.server.dosopt.seminar.dto.request.post.PostCreateRequest;
import com.server.dosopt.seminar.dto.request.post.PostUpdateRequest;
import com.server.dosopt.seminar.dto.response.post.PostGetResponse;
import com.server.dosopt.seminar.enums.SuccessMessage;
import com.server.dosopt.seminar.service.PostService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private static final String CUSTOM_AUTH_ID = "X-Auth-id";  // 상수(static final)로 관리

    @PostMapping
    public CreatedApiResponse createPost(@RequestHeader(CUSTOM_AUTH_ID) Long memberId,
                                        @RequestBody PostCreateRequest request) {
        String postId = postService.create(request, memberId);
        URI location = URI.create("/api/post" + postId);
        return CreatedApiResponse.success(CREATE_POST_SUCCESS, location);
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostGetResponse> getPostById(@PathVariable Long postId) {
        return ApiResponse.success(GET_POST_INFO_SUCCESS, postService.getById(postId));
    }

    @GetMapping
    public ApiResponse<List<PostGetResponse>> getPosts(@RequestHeader(CUSTOM_AUTH_ID) Long memberId){
        return ApiResponse.success(GET_POST_LIST_SUCCESS, postService.getPosts(memberId));
    }

    @PatchMapping("/{postId}")
    public ApiResponse updatePost(@PathVariable Long postId,
                                           @RequestBody PostUpdateRequest request) {
        postService.update(request, postId);
        return ApiResponse.success(UPDATE_POSt_SUCCESS);
    }

    @DeleteMapping("/{postId}")
    public ApiResponse deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return ApiResponse.success(DELETE_MEMBER_SUCCESS);
    }
}
