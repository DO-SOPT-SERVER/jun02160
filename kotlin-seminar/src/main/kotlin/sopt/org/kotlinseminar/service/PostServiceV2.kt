package sopt.org.kotlinseminar.service

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import sopt.org.kotlinseminar.common.exception.BusinessException
import sopt.org.kotlinseminar.domain.Post
import sopt.org.kotlinseminar.dto.request.post.PostCreateRequest
import sopt.org.kotlinseminar.external.S3Service
import sopt.org.kotlinseminar.repository.MemberRepository
import sopt.org.kotlinseminar.repository.PostJpaRepository
import java.io.IOException

private const val POST_IMAGE_FOLDER_NAME = "posts/"

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class PostServiceV2(
        private val postRepository: PostJpaRepository,
        private val memberRepository: MemberRepository,
        private val s3Service: S3Service
) {

    @Transactional
    fun createV2(request: PostCreateRequest, image: MultipartFile, memberId: Long): String {
        try {
            val imageUrl: String = s3Service.uploadImage(POST_IMAGE_FOLDER_NAME, image)
            val member = memberRepository.findByIdOrThrow(memberId)
            val post = postRepository.save(
                    Post(member = member,
                            title = request.title,
                            content = request.content,
                            imageUrl = imageUrl)
            )
            return post.id.toString()
        } catch (e: RuntimeException) {
            throw RuntimeException(e.message)
        } catch (e: IOException) {
            throw RuntimeException(e.message)
        }
    }

    @Transactional
    fun deleteByIdV2(postId: Long) {
        try {
            val post = postRepository.findById(postId)
                    .orElseThrow { BusinessException("해당하는 게시글이 없습니다.") }
            s3Service.deleteImage(post.imageUrl)
            postRepository.deleteById(postId)
        } catch (e: java.lang.RuntimeException) {
            throw java.lang.RuntimeException(e.message)
        } catch (e: IOException) {
            throw java.lang.RuntimeException(e.message)
        }
    }
}