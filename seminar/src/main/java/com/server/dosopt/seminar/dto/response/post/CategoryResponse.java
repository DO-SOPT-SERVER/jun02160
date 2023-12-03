package com.server.dosopt.seminar.dto.response.post;

import com.server.dosopt.seminar.domain.Category;

public record CategoryResponse(
        Short id,
        String content
) {

    public static CategoryResponse of(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getContent());
    }
}
