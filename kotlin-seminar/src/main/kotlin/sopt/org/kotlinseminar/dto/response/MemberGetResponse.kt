package sopt.org.kotlinseminar.dto.response

import sopt.org.kotlinseminar.domain.Member
import sopt.org.kotlinseminar.domain.Sopt

/**
 * 팩토리 메서드를 통해서만 객체를 생성하도록 private constructor 설정
 * -> 생성자를 직접 호출할 수 없음
 */
class MemberGetResponse private constructor(
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


