package com.common.common.exception.model;

import com.common.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotFoundException extends CustomException {

    public NotFoundException(ErrorCode error) {
        super(error);
    }
}