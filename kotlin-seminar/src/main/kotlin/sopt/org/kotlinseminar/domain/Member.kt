package sopt.org.kotlinseminar.domain

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    var name: String = "",

    nickname: String,

    @Column
    var age: Int,

    sopt: Sopt


) {

    @Column
    var nickname = nickname
        protected set

    @Embedded
    var sopt = sopt
        protected set
}