package com.server.dosopt.seminar.domain;

import jakarta.persistence.*;   // java 17버전 이상부터는 jakarta로 변경
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)   // 엔티티에는 반드시 고유 식별자(ID)가 존재해야 핢 -> 생성전략을 함께 지정
    private Long id;
    private String name;
    private String nickname;
    private int age;

    @Embedded   // 값 타입을 사용하는 곳에 붙이는 어노테이션
    private SOPT sopt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)   // CASCADE를 지정하기 위해서는 양방향 매핑하여 "One" 쪽에 달아주기
    private final List<Post> posts = new ArrayList<>();

    @Builder   // 빌더패턴을 적용하여 객체 생성 가능
    public Member(String name, String nickname, int age, SOPT sopt) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.sopt = sopt;
    }
}
