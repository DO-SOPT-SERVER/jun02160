package sopt.org.kotlinseminar.domain

import lombok.AccessLevel
import lombok.Builder
import lombok.NoArgsConstructor

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
class Person(
    var lastName: String?,
    var firstName: String?
)   // 자바의 멤버 대신 프로퍼티 형식으로 작성하면 Getter, Setter의 역할까지 한번에 처리해준다