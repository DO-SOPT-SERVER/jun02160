package sopt.org.kotlinseminar.repository

import org.springframework.data.jpa.repository.JpaRepository
import sopt.org.kotlinseminar.domain.ServiceMember
import java.util.*


interface ServiceMemberRepository: JpaRepository<ServiceMember, Long> {
    fun findByNickname(nickname: String): Optional<ServiceMember>
}