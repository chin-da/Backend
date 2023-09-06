package com.common.common.exception.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.httpStatus = status;
    }

    public int getStatusCode() {
        return httpStatus.value();
    }
}
