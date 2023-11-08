package com.server.dosopt.seminar.dto.request.member;

import com.server.dosopt.seminar.domain.SOPT;
import lombok.Data;

//@Data   // Getter, Setter, ToString 메서드까지 모두 지원해주는 롬복 어노테이션
public record MemberCreateRequest(

    String name,
    String nickname,
    int age,
    SOPT sopt
) {
}
