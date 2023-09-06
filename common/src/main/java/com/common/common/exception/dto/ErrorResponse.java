package com.common.common.exception.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse<T> {

    private final int code;
    private final String message;

    public static ErrorResponse of(final int code, final String message) {
        return new ErrorResponse<>(code, message);
    }
}