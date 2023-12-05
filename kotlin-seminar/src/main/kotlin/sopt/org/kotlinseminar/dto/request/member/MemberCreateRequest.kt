package sopt.org.kotlinseminar.dto.request.member

import sopt.org.kotlinseminar.domain.Sopt

data class MemberCreateRequest(
    val name: String = "",
    val nickname: String = "",
    val age: Int,
    val sopt: Sopt
)
