package sopt.org.kotlinseminar.controller

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sopt.org.kotlinseminar.dto.request.post.PostCreateRequest
import sopt.org.kotlinseminar.dto.request.post.PostUpdateRequest
import sopt.org.kotlinseminar.dto.response.post.PostGetResponse
import sopt.org.kotlinseminar.enums.Constant.CUSTOM_AUTH_ID
import sopt.org.kotlinseminar.service.PostService
import java.net.URI

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
class PostController(
        private val postService: PostService
) {

    @PostMapping
    fun createPost(@RequestHeader(CUSTOM_AUTH_ID) memberId: Long,
                    @RequestBody request: PostCreateRequest): ResponseEntity<Void>? {
        val postId = postService.create(request, memberId)
        val location: URI = URI.create("/api/post" + postId)
        return ResponseEntity.created(location).build()
    }

    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId: Long): ResponseEntity<PostGetResponse> {
        return ResponseEntity.ok(postService.getById(postId))
    }

    @GetMapping
    fun getPosts(@RequestHeader(CUSTOM_AUTH_ID) memberId: Long): ResponseEntity<List<PostGetResponse>> {
        return ResponseEntity.ok(postService.getPosts(memberId))
    }

    @PatchMapping("/{postId}")
    fun updatePost(@PathVariable postId: Long,
                    @RequestBody request: PostUpdateRequest): ResponseEntity<Void> {
        postService.update(request, postId)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<Void> {
        postService.delete(postId)
        return ResponseEntity.noContent().build()
    }
}