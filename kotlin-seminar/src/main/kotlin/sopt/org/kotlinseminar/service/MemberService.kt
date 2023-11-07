package sopt.org.kotlinseminar.service

import jakarta.persistence.EntityNotFoundException
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sopt.org.kotlinseminar.domain.Member
import sopt.org.kotlinseminar.dto.request.MemberCreateRequest
import sopt.org.kotlinseminar.dto.response.MemberGetResponse
import sopt.org.kotlinseminar.repository.MemberRepository
import java.util.stream.Collectors


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class MemberService(
        private val memberRepository: MemberRepository
) {

    @Transactional
    fun create(request: MemberCreateRequest): String {
        val member = Member(
            name = request.name,
            nickname = request.nickname,
            age = request.age,
            sopt = request.sopt
        )
        return memberRepository.save(member).id.toString();  // nullable 대비
    }

    // 조회
    fun getMemberByIdV1(memberId: Long): MemberGetResponse {
        return MemberGetResponse.of(findMemberById(memberId))
    }

    fun getMemberByIdV2(memberId: Long): MemberGetResponse {
        return MemberGetResponse.of(memberRepository.findByIdOrThrow(memberId))
    }

    fun getMembers(): List<MemberGetResponse> {
        return memberRepository.findAll()
            .stream()
            .map<MemberGetResponse>(MemberGetResponse::of)
            .collect(Collectors.toList())
    }

    private fun findMemberById(memberId: Long): Member {
        return memberRepository.findById(memberId)   // nullable로 열어둘 경우
            .orElseThrow{ EntityNotFoundException("존재하지 않는 회원입니다.") }
    }

}