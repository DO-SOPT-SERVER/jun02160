package com.server.dosopt.seminar.controller;

import static com.server.dosopt.seminar.enums.SuccessMessage.CREATE_POST_SUCCESS;
import static com.server.dosopt.seminar.enums.SuccessMessage.DELETE_MEMBER_SUCCESS;
import static com.server.dosopt.seminar.enums.SuccessMessage.GET_POST_INFO_SUCCESS;
import static com.server.dosopt.seminar.enums.SuccessMessage.GET_POST_LIST_SUCCESS;
import static com.server.dosopt.seminar.enums.SuccessMessage.UPDATE_POSt_SUCCESS;

import com.server.dosopt.seminar.common.response.ApiResponse;
import com.server.dosopt.seminar.dto.request.post.PostCreateRequest;
import com.server.dosopt.seminar.dto.request.post.PostUpdateRequest;
import com.server.dosopt.seminar.dto.response.post.PostGetResponse;
import com.server.dosopt.seminar.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private static final String CUSTOM_AUTH_ID = "X-Auth-id";  // 상수(static final)로 관리
    private static final String POST_REQUEST_URI = "/api/post";


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createPost(@RequestHeader(CUSTOM_AUTH_ID) Long memberId,
                                    @RequestBody PostCreateRequest request, HttpServletResponse response) {
        String postId = postService.create(request, memberId);
        URI location = URI.create(POST_REQUEST_URI + postId);
        response.setHeader("Location", location.toString());  // 응답 헤더에 Location 값 설정
        return ApiResponse.success(CREATE_POST_SUCCESS);
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PostGetResponse> getPostById(@PathVariable Long postId) {
        return ApiResponse.success(GET_POST_INFO_SUCCESS, postService.getById(postId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<PostGetResponse>> getPosts(@RequestHeader(CUSTOM_AUTH_ID) Long memberId){
        return ApiResponse.success(GET_POST_LIST_SUCCESS, postService.getPosts(memberId));
    }

    @PatchMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse updatePost(@PathVariable Long postId,
                                    @RequestBody PostUpdateRequest request) {
        postService.update(request, postId);
        return ApiResponse.success(UPDATE_POSt_SUCCESS);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return ApiResponse.success(DELETE_MEMBER_SUCCESS);
    }
}
