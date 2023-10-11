package com.server.dosopt.seminar.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final int code;
    private final String status;
    private final boolean success;
    private T data;

    // 성공
    public static ApiResponse success(HttpStatus httpStatus) {
        return ApiResponse.builder()
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .success(true)
                .build();
    }

    public static <T> ApiResponse<T> success(HttpStatus httpStatus, T data) {
        return ApiResponse.<T>builder()
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .success(true)
                .data(data)
                .build();
    }

    public static ApiResponse error(HttpStatus httpStatus) {
        return ApiResponse.builder()
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .success(false)
                .build();
    }

}
