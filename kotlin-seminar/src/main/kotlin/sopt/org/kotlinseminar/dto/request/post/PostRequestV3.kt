package sopt.org.kotlinseminar.dto.request.post

data class PostRequestV3(
        @ValidTitle
        val title: String,
        val content: String
)
