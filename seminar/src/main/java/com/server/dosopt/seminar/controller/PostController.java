package com.server.dosopt.seminar.controller;

import com.server.dosopt.seminar.dto.request.post.PostCreateRequest;
import com.server.dosopt.seminar.dto.request.post.PostUpdateRequest;
import com.server.dosopt.seminar.dto.response.post.PostGetResponse;
import com.server.dosopt.seminar.service.PostService;
import jakarta.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

@Slf4j
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private static final String CUSTOM_AUTH_ID = "X-Auth-id";  // 상수(static final)로 관리

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createPost(@Valid @RequestBody PostCreateRequest request, Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        log.info("memberId: {}", memberId);
        String postId = postService.create(request, memberId);

        URI location = URI.create("/api/post" + postId);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PostGetResponse> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getById(postId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PostGetResponse>> getPosts(@RequestHeader(CUSTOM_AUTH_ID) Long memberId){
        return ResponseEntity.ok(postService.getPosts(memberId));
    }

    @PatchMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> updatePost(@PathVariable Long postId,
                                           @RequestBody PostUpdateRequest request) {
        postService.update(request, postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.noContent().build();
    }
}
