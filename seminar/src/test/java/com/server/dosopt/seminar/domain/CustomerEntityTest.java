package com.server.dosopt.seminar.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.server.dosopt.seminar.common.util.auth.filter.CustomAccessDeniedHandler;
import com.server.dosopt.seminar.common.util.auth.filter.CustomJwtAuthenticationEntryPoint;
import com.server.dosopt.seminar.common.util.auth.filter.JwtAuthenticationFilter;
import com.server.dosopt.seminar.repository.CustomerJpaRepository;
import javax.print.attribute.standard.MediaSize.NA;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerEntityTest {

//    @Autowired
//    CustomerJpaRepository customerJpaRepository;

    @Nested  // Inner Class(중첩 클래스)를 만들어 테스트하기 위해 추가하는 어노테이션
    @DisplayName("Customer 생성 관련 Test")
    class CustomerCreateTest {

        // 클래스 변수로 선언하여 여러 테스트에서 공용
        private static final String NAME = "박예준";
        private static final int INVALID_AGE = -1;
        private static final int AGE = 22;
        private static final String NICKNAME = "jun";

        @DisplayName("나이가 1 미만 200 초과인 경우 Customer 객체 생성에 실패한다.")
        @Test
        void createInvalidAgeCustomerTest() {
            assertThatThrownBy(() ->
                    CustomerEntity.builder()
                            .name(NAME)
                            .age(INVALID_AGE)
                            .nickname(NICKNAME)
                            .build())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("나이는 1살부터 200살 사이여야 합니다.");   // 처리해준 예외 메시지와 일치하는지 검사
        }

        @DisplayName("닉네임은 null인 경우 Customer 객체 생성에 실패한다.")
        @Test
        void createNullNicknameCustomerTest() {
            assertThatThrownBy(() ->
                    CustomerEntity.builder()
                            .name(NAME)
                            .age(AGE)
                            .nickname(null)
                            .build())
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("닉네임은 null이면 안됩니다.");
        }

        @DisplayName("이름이 null인 경우 Customer 객체 생성에 실패한다.")
        @Test
        void createNameCustomerTest() {
            assertThatThrownBy(() ->
                    CustomerEntity.builder()
                    .name(NAME)
                    .age(AGE)
                    .nickname(null)
                    .build())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 null이면 안됩니다.");
        }

    }

}