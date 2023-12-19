package sopt.org.kotlinseminar.controller

import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import sopt.org.kotlinseminar.dto.request.post.PostCreateRequest
import sopt.org.kotlinseminar.dto.request.post.PostUpdateRequest
import sopt.org.kotlinseminar.dto.response.post.PostGetResponse
import sopt.org.kotlinseminar.enums.Constant.CUSTOM_AUTH_ID
import sopt.org.kotlinseminar.service.PostService
import sopt.org.kotlinseminar.service.PostServiceV2
import java.net.URI

@RestController
@RequestMapping("/api/v2/post")
@RequiredArgsConstructor
class PostControllerV2(
        private val postService: PostServiceV2
) {

    @PostMapping
    fun createPost(@RequestHeader(CUSTOM_AUTH_ID) memberId: Long,
                    @RequestPart image: MultipartFile,
                    @Valid @RequestBody request: PostCreateRequest): ResponseEntity<Void>? {
        val postId = postService.createV2(request, image, memberId)
        val location: URI = URI.create("/api/v2/post" + postId)
        return ResponseEntity.created(location).build()
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<Void> {
        postService.deleteByIdV2(postId)
        return ResponseEntity.noContent().build()
    }
}