package com.server.dosopt.seminar.dto.request.member;

import com.server.dosopt.seminar.domain.Part;
import lombok.Data;

@Data
public class MemberProfileUpdateRequest {

    private int generation;
    private Part part;
}
