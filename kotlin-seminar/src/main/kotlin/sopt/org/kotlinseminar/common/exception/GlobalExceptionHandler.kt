package sopt.org.kotlinseminar.common.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: java.lang.IllegalArgumentException?): ResponseEntity<Void> {
        return ResponseEntity.badRequest().build()
    }


    /**
     * 커스텀 Exception에 대한 예외처리 (한꺼번에 처리 가능)
     */
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException?): ResponseEntity<Void> {
        return ResponseEntity.badRequest().build()
    }
}