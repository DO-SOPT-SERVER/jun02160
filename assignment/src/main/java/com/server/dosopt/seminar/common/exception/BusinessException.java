package com.server.dosopt.seminar.common.exception;

import com.server.dosopt.seminar.enums.ErrorMessage;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorMessage errorMessage;
    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public int getHttpStatus() {
        return errorMessage.getHttpStatusCode();
    }
}
