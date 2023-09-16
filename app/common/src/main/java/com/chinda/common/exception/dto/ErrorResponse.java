package com.chinda.common.exception.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse<T> {

    private final int code;
    private final String message;
    private final T data;

    public static ErrorResponse of(final int code, final String message) {
        return new ErrorResponse<>(code, message, null);
    }

    public static <T> ErrorResponse<T> of(final int code, final String message, final T data) {
        return new ErrorResponse<T>(code, message, data);
    }
}