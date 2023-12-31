package com.chinda.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends HttpException {

    public NotFoundException(final String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}