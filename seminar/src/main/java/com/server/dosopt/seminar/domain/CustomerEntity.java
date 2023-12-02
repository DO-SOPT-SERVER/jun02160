package com.server.dosopt.seminar.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "customer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CustomerEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * name VS nickname의 차이점은?  예외가 발생하는 시점
     * -> 둘다 not null로 들어가지만, 서로 다른 Exception이 발생하는 것을 볼 수 있음
     *
     * 두 경우 모두 DB에 쿼리를 날릴 때 (= 실제 DB에 값이 들어갈 때) Exception 발생
     *
     * - @NotNull : ConstraintViolationException
     * - @Column(nullable = false) : SQLIntegrityConstraintViolationException
     */
    @NotNull
    private String name;

    @Column(nullable = false)
    private String nickname;

    private int age;

    // 생성자를 활용하여 객체 생성 시점에서 유효성을 검증할 수 있다!
    @Builder
    public CustomerEntity(String name, String nickname, int age) {
        validateName(name);
        validateAge(age);
        validateNickname(nickname);

        this.name = name;
        this.nickname = nickname;
        this.age = age;
    }

    // org.springframework.util의 Assert 인터페이스를 이용하여 유효성 검증 메서드 구현!
    private void validateName(String name) {
        Assert.notNull(name, "이름은 null이면 안됩니다.");
    }

    private void validateAge(int age) {
        Assert.isTrue(age > 0 && age < 200, "나이는 1살부터 200살 사이여야 합니다.");
    }

    private void validateNickname(String nickname) {
        Assert.notNull(nickname, "닉네임은 null이면 안됩니다.");
    }
}
