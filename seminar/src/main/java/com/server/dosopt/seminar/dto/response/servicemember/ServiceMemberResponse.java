package com.server.dosopt.seminar.dto.response.servicemember;

import lombok.Builder;

@Builder
public record ServiceMemberResponse(
        String nickname,
        String accessToken
) {
}
