package com.server.dosopt.seminar.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

/**
 * 공통적으로 관리할 수 있는 속성들을 하나로 묶어서, 하나의 테이블 내에 너무 많은 필드들을 관리하여
 * 가독성이 떨어지고 유지보수성이 낮은 문제를 개선할 수 있다!
 * -> 값 타입을 잘 사용할수록 좋은 애플리케이션을 설계할 수 있음
 */
@Embeddable  // 임베디드 타입 -> 값 타입으로 지정
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 지연로딩을 위해 기본 생성자 필요 (이때, private으로 접근 레벨을 지정하면 프록시 객체를 생성하지 못하므로 protected 이상으로 만들어줘야 함)
@AllArgsConstructor
public class SOPT {

    private int generation;

    @Enumerated(value = STRING)   // STRING으로 명시해주지 않으면 인덱스(smallint 타입)로 저장됨
    private Part part;
}
