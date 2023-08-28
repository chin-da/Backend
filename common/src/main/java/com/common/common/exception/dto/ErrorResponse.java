package com.common.common.exception.dto;

import com.common.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse<T> {

    private final int code;
    private final String message;

    public static ErrorResponse error(ErrorCode error) {
        return new ErrorResponse<>(error.getHttpStatusCode(), error.getMessage());
    }

    public static ErrorResponse error(ErrorCode error, String message) {
        return new ErrorResponse<>(error.getHttpStatusCode(), message);
    }
}