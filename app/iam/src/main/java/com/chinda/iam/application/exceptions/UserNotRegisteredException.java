package com.chinda.iam.application.exceptions;

import com.chinda.common.exception.HttpException;
import org.springframework.http.HttpStatus;

public final class UserNotRegisteredException extends HttpException {

    public UserNotRegisteredException(final String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
