package sopt.org.kotlinseminar.domain

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CustomerEntityTest {

    companion object {
        private const val NAME = "홍길동"
        private const val INVALID_AGE = -1
        private const val AGE = 20
        private const val NICKNAME = "hong"
    }

    @Nested
    @DisplayName("Customer 생성 관련 Test")
    internal class CustomerCreateTest {
        @DisplayName("나이가 1이상 200이하인 경우 Customer 객체 생성을 실패한다.")
        @Test
        fun createInvalidAgeCustomerTest() {
            assertThatThrownBy {
                CustomerEntity(
                        name = NAME,
                        age = INVALID_AGE,
                        nickname = NICKNAME
                )}
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("나이는 1살부터 200살 사이로 설정해야합니다.")
        }

        @DisplayName("닉네임은 null인 경우 Customer 객체 생성을 실패한다.")
        @Test
        fun createNullNicknameCustomerTest() {
            assertThatThrownBy {
                CustomerEntity(
                        name = NAME,
                        age = INVALID_AGE,
                        nickname = NICKNAME
                )}
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("닉네임은 널이면 안됩니다.")
        }

        @DisplayName("이름은 null인 경우 Customer 객체 생성을 실패한다.")
        @Test
        fun createNullNameCustomerTest() {
            assertThatThrownBy {
                CustomerEntity(
                        name = NAME,
                        age = INVALID_AGE,
                        nickname = NICKNAME
                )}
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("이름은 널이면 안됩니다.")
        }
    }

}