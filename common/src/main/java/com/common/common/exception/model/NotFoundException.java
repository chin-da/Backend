package com.common.common.exception.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends CustomException {

    public NotFoundException(final String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}