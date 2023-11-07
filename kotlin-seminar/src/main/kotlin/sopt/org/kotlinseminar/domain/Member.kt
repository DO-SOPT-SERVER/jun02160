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

    name: String,

    nickname: String,

    age: Int,

    sopt: Sopt
) {

    @Column
    var name = name
        protected set

    @Column
    var nickname = nickname
        protected set   // setter를 외부에서 호출하지 못하도록 막아둔다

    @Column
    var age = age
        protected set

    @Embedded
    var sopt = sopt
        protected set
}