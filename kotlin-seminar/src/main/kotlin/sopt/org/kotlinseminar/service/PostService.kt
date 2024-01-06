package sopt.org.kotlinseminar.service

import jakarta.persistence.EntityNotFoundException
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sopt.org.kotlinseminar.domain.Post
import sopt.org.kotlinseminar.dto.request.post.PostCreateRequest
import sopt.org.kotlinseminar.dto.request.post.PostUpdateRequest
import sopt.org.kotlinseminar.dto.response.post.PostGetResponse
import sopt.org.kotlinseminar.repository.MemberRepository
import sopt.org.kotlinseminar.repository.PostJpaRepository

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class PostService(
        private val postRepository: PostJpaRepository,
        private val memberRepository: MemberRepository
) {

    @Transactional
    fun create(request: PostCreateRequest, memberId: Long): String {
        val member = memberRepository.findByIdOrThrow(memberId)
        val post = postRepository.save(
                Post(member = member,
                    title = request.title,
                    content = request.content,
                    imageUrl = null)
        )
        return post.id.toString()
    }

    fun getById(postId: Long): PostGetResponse {
        val post = findPostById(postId)
        return PostGetResponse.of(post);
    }

    fun getPosts(memberId: Long): List<PostGetResponse> {
        return postRepository.findAllByMemberId(memberId).stream()
                .map { PostGetResponse.of(it) }
                .toList()
    }

    @Transactional
    fun update(request: PostUpdateRequest, postId: Long) {
        val post = findPostById(postId)
        post.updateContent(request.content)
    }

    @Transactional
    fun delete(postId: Long) {
        postRepository.deleteById(postId)
    }

    private fun findPostById(postId: Long): Post = postRepository.findById(postId)
            .orElseThrow { EntityNotFoundException("존재하지 않는 게시물입니다.") }


}