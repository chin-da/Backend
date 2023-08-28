package com.common.common.exception.model;

import com.common.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UnauthorizedException extends CustomException {

    public UnauthorizedException(ErrorCode error) {
        super(error);
    }
}