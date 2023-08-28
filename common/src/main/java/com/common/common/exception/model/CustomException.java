package com.common.common.exception.model;

import com.common.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode error;

    public CustomException(ErrorCode error) {
        super(error.getMessage());
        this.error = error;
    }

    public int getHttpStatus() {
        return error.getHttpStatusCode();
    }
}
