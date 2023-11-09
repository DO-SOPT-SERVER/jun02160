package com.server.dosopt.seminar.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.server.dosopt.seminar.enums.ErrorMessage;
import com.server.dosopt.seminar.enums.SuccessMessage;
import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * TODO 공통 Response 클래스 -> Record 타입으로 변경할 것
 * @param <T>
 */
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"code", "status", "success", "data"})
public class ApiResponse<T> {

    private final int code;
    private final String status;
    private final boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    // 성공
    public static ApiResponse success(SuccessMessage successMessage) {
        return ApiResponse.builder()
                .code(successMessage.getHttpStatusCode())
                .status(successMessage.getMessage())
                .success(true)
                .build();
    }

    public static <T> ApiResponse<T> success(SuccessMessage successMessage, T data) {
        return ApiResponse.<T>builder()
                .code(successMessage.getHttpStatusCode())
                .status(successMessage.getMessage())
                .success(true)
                .data(data)
                .build();
    }


    public static ApiResponse error(ErrorMessage errorMessage) {
        return ApiResponse.builder()
                .code(errorMessage.getHttpStatusCode())
                .status(errorMessage.getMessage())
                .success(false)
                .build();
    }

    @Builder
    public static class CreatedApiResponse extends ResponseEntity {

        private final BodyBuilder bodyBuilder;

        private CreatedApiResponse(BodyBuilder bodyBuilder) {
            super(HttpStatus.CREATED);
            this.bodyBuilder = bodyBuilder;
        }
        // 201 Created
        public static CreatedApiResponse success(SuccessMessage successMessage, URI location) {
            return CreatedApiResponse.builder()
                    .bodyBuilder(created(location))
                    .build();
        }

    }
}
