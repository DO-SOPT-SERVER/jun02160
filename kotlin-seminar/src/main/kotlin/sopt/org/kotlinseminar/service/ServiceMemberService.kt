package sopt.org.kotlinseminar.service

import lombok.RequiredArgsConstructor
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sopt.org.kotlinseminar.domain.ServiceMember
import sopt.org.kotlinseminar.dto.request.servicemember.ServiceMemberRequest
import sopt.org.kotlinseminar.repository.ServiceMemberRepository
import java.lang.RuntimeException


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ServiceMemberService(
        private val serviceMemberRepository: ServiceMemberRepository,
        private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun create(request: ServiceMemberRequest): String {
        val serviceMember: ServiceMember = ServiceMember(
                nickname = request.nickname,
                password = passwordEncoder.encode(request.password))
        serviceMemberRepository.save(serviceMember)

        return serviceMember.id.toString()
    }

    fun signIn(request: ServiceMemberRequest) {
        val serviceMember: ServiceMember = serviceMemberRepository.findByNickname(request.nickname)
                .orElseThrow { RuntimeException("해당하는 회원이 없습니다.") }

        if (!passwordEncoder.matches(request.password, serviceMember.password)) {
            throw RuntimeException ("비밀번호가 일치하지 않습니다.")
        }
    }
}