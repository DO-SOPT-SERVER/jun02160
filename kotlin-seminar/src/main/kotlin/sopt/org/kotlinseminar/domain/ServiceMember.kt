package sopt.org.kotlinseminar.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ServiceMember(
        nickname: String,
        password: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L
) {

    @Column(nullable = false)
    var nickname = nickname
        protected set

    @Column(nullable = false)
    var password = password
        protected set
}