package sopt.org.kotlinseminar.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "customer")
class CustomerEntity(
        name: String,
        age: Int,

        nickname: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L
) {

    @Column
    @NotNull
    var name = name
        protected set

    @Column
    var age = age
        protected set

    @Column(nullable = false)
    var nickname = nickname
        protected set
}