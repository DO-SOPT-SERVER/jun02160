package com.server.dosopt.seminar.common.exception;

import com.server.dosopt.seminar.common.response.ApiResponse;
import com.server.dosopt.seminar.enums.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse handleIllegalArgumemtException(final IllegalArgumentException e) {
        return ApiResponse.error(ErrorMessage.INTERNAL_SERVER_ERROR);
    }


    /**
     * 커스텀 Exception에 대한 예외처리 (한꺼번에 처리 가능)
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse> handleBusinessException(BusinessException e) {
        log.error("Custom BusinessException occured: {}", e.getMessage(), e);

        return ResponseEntity.status(e.getHttpStatus())
                .body(ApiResponse.error(e.getErrorMessage()));
    }
}
