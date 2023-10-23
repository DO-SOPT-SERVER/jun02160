package sopt.org.kotlinseminar.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sopt.org.kotlinseminar.domain.Member
import sopt.org.kotlinseminar.dto.request.MemberCreateRequest
import sopt.org.kotlinseminar.repository.MemberRepository

@Service
@Transactional(readOnly = true)
class MemberService {

    @Autowired
    internal var memberRepository: MemberRepository? = null

    @Transactional
    fun create(request: MemberCreateRequest): String {
        val member = Member(
            name = request.name,
            nickname = request.nickname,
            age = request.age,
            sopt = request.sopt
        )
        return memberRepository?.save(member)?.id.toString();  // nullable 대비
    }

}