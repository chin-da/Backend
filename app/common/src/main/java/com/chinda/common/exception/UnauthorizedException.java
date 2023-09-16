package com.chinda.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnauthorizedException extends HttpException {

    public UnauthorizedException(final String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}