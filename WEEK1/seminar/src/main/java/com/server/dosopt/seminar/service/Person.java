package com.server.dosopt.seminar.service;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder   // 빌더패턴 적용 (롬복 어노테이션으로 제공)
public class Person {

    private String lastName;
    private String firstName;

    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
