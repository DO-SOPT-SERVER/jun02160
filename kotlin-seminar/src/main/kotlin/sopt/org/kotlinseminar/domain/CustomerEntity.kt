package sopt.org.kotlinseminar.domain

import io.jsonwebtoken.lang.Assert
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "customer")
class CustomerEntity(

        @NotNull
        var name: String,
        var age: Int,

        nickname: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L
) {

    @Column(nullable = false)
    var nickname = nickname
        protected set

    init {
        validateName(name)
        validateAge(age)
        validateNickname(nickname)

        this.name = name
        this.age = age
        this.nickname = nickname
    }

    private fun validateName(name: String) {
        Assert.notNull(nickname, "닉네임은 널이면 안 됩니다.")
    }

    private fun validateAge(age: Int) {
        Assert.isTrue(age > 0 && age < 200, "나이는 1살부터 200살 사이여야 합니다.")
    }

    private fun validateNickname(nickname: String) {
        Assert.notNull(nickname, "닉네임은 널이면 안 됩니다.")
    }
}