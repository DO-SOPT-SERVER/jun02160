package com.server.dosopt.seminar.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorMessage {

    /**
     * 400 BAD REQUEST
     */

    // Common
    REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),

    /**
     * 404 NOT FOUND
     */
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."),
    NOT_FOUND_CATEGORY(HttpStatus.NOT_FOUND, "해당하는 카테고리가 없습니다."),

    /**
     * 500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다")

    ;


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
