package sopt.org.kotlinseminar.dto.request.post

data class PostCreateRequest(
        val title: String = "",
        val content: String = ""
)