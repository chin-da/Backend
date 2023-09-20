package com.chinda.iam.application.exceptions;

import com.chinda.common.exception.HttpException;
import org.springframework.http.HttpStatus;

public class DuplicateNicknameException extends HttpException {

    public DuplicateNicknameException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
