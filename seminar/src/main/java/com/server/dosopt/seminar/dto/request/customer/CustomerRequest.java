package com.server.dosopt.seminar.dto.request.customer;

public record CustomerRequest(
        String name,
        String nickname,
        int age
) {
}
