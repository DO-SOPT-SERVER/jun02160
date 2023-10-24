package sopt.org.kotlinseminar.dto.response

import sopt.org.kotlinseminar.domain.Member
import sopt.org.kotlinseminar.domain.Sopt

class MemberGetResponse(
    val name: String = "",
    val nickname: String = "",
    val age: Int,
    var sopt: Sopt
) {

    companion object {
        fun of(member: Member): MemberGetResponse {
            return MemberGetResponse(
                name = member.name,
                nickname = member.nickname,
                age = member.age,
                sopt = member.sopt
            )
        }
    }
}


