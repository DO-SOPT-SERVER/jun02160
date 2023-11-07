package com.server.dosopt.seminar.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * POST는 하나의 카테고리만 가질 수 있다 => 일대일 관계
 * ** Post-Category 엔티티는 연관관계 어노테이션 사용없이 일대일 관계를 구현해보자!
 *   - 실제 DB 상에 테이블 매핑 X
 *   - JOIN 연산이 불가
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id @GeneratedValue
    private Short id;

    private String content;

}
