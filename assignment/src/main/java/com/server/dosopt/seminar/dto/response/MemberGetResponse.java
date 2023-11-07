package com.server.dosopt.seminar.dto.response;

import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.domain.SOPT;

/**
 * Kotlin의 Data Class와 유사
 */
public record MemberGetResponse (
    String name,
    String nickname,
    int age,
    SOPT sopt
) {
    public static MemberGetResponse of(Member member) {
        return new MemberGetResponse(
                member.getName(),
                member.getNickname(),
                member.getAge(),
                member.getSopt()
        );
    }
}
