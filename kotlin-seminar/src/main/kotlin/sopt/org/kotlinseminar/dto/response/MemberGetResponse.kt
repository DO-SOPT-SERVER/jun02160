package sopt.org.kotlinseminar.dto.response

import sopt.org.kotlinseminar.domain.Sopt

data class MemberGetResponse(
    val name: String = "",
    val nickname: String = "",
    val age: Int,
    val sopt: Sopt
)
