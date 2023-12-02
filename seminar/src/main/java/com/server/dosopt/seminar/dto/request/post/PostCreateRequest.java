package com.server.dosopt.seminar.dto.request.post;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PostCreateRequest(

        @NotNull   // 값이 null인 경우에 대한 형식적 Validation 적용
        @Pattern(regexp = "^[a-zA-Z0-9가-힣\\s]*$", message = "제목은 한글, 영문, 숫자만 입력 가능합니다.")
        @Size(min = 1, max = 10, message = "제목은 1글자 이상, 10글자 이하여야 합니다.")
        String title,

        String content
) {

        @AssertTrue(message = "내용은 10자 이상으로 작성해주세요")
        public boolean isValidContent() {
                try {
                        return this.content.length() >= 10;
                } catch (RuntimeException e) {
                        return  false;
                }
        }

        public void validateTitle() {
                if (this.title.isBlank()) {
                        throw new IllegalArgumentException("제목은 공백으로만 이뤄질 수 없습니다.");
                }
        }
}
