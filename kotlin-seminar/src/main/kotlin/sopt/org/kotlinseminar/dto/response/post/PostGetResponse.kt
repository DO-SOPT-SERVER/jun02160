package sopt.org.kotlinseminar.dto.response.post

import sopt.org.kotlinseminar.domain.Post

data class PostGetResponse(
        val title: String,
        val content: String
) {

    companion object {
        fun of(post: Post): PostGetResponse {
            return PostGetResponse(
                    title = post.title,
                    content = post.content
            )
        }
    }
}