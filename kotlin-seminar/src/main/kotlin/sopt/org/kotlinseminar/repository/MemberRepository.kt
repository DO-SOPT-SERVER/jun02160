package sopt.org.kotlinseminar.repository

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import sopt.org.kotlinseminar.domain.Member

interface MemberRepository : JpaRepository<Member, Long> {

    fun findByIdOrThrow(id: Long): Member {
        return findById(id).orElseThrow {
            EntityNotFoundException("존재하지 않는 회원입니다.")
        }
    }
}