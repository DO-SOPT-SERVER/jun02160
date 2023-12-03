package com.server.dosopt.seminar.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessMessage {

    /**
     * 200 OK
     */
    GET_MEMBER_INFO_SUCCESS(HttpStatus.OK, "회원 정보 조회에 성공했습니다."),
    GET_MEMBER_LIST_SUCCESS(HttpStatus.OK, "회원 리스트 조회에 성공했습니다."),
    UPDATE_MEMBER_SUCCESS(HttpStatus.OK, "회원 수정에 성공했습니다."),
    DELETE_MEMBER_SUCCESS(HttpStatus.OK, "회원 삭제에 성공했습니다."),

    GET_POST_INFO_SUCCESS(HttpStatus.OK, "게시물 정보 조회에 성공했습니다."),
    GET_POST_LIST_SUCCESS(HttpStatus.OK, "게시물 리스트 조회에 성공했습니다."),
    UPDATE_POSt_SUCCESS(HttpStatus.OK, "게시물 수정에 성공했습니다."),
    DELETE_POST_SUCCESS(HttpStatus.OK, "게시물 삭제에 성공했습니다."),



    /**
     * 201 Created
     */
    CREATE_MEMBER_SUCCESS(HttpStatus.CREATED, "회원 생성에 성공했습니다."),
    CREATE_POST_SUCCESS(HttpStatus.CREATED, "게시물 생성에 성공했습니다."),

    /**
     * 204 No Content
     */
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
