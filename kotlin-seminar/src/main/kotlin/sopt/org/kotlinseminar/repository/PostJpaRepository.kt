package sopt.org.kotlinseminar.repository

import org.springframework.data.jpa.repository.JpaRepository
import sopt.org.kotlinseminar.domain.Post

interface PostJpaRepository: JpaRepository<Post, Long> {

    fun findAllByMemberId(memberId: Long): List<Post>
}