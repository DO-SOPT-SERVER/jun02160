package com.server.dosopt.seminar.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleIllegalArgumemtException(final IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();   // 400 응답으로 반환
    }

    /**
     * 커스텀 Exception에 대한 예외처리 (한꺼번에 처리 가능)
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Void> handleBusinessException(BusinessException e) {
        return ResponseEntity.badRequest().build();
    }
}
