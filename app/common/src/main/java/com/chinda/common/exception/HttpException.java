package com.chinda.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException {

    private final HttpStatus httpStatus;

    public HttpException(HttpStatus status, String message) {
        super(message);
        this.httpStatus = status;
    }

    public int getStatusCode() {
        return httpStatus.value();
    }
}
