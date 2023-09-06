package com.common.common.exception.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnauthorizedException extends CustomException {

    public UnauthorizedException(final String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}