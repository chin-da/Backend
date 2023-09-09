package com.api.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final int code;
    private T data;

//    public static ApiResponse success(Success success) {
//        return new ApiResponse<>(success.getHttpStatusCode());
//    }
//
//        return new ApiResponse<T>(success.getHttpStatusCode(),success.getMessage(),data);
//}
//
//    public static ApiResponse error(Error error) {
//        return new ApiResponse<>(error.getHttpStatusCode(), error.getMessage());
//    }
//
//    public static ApiResponse error(Error error, String message) {
//        return new ApiResponse<>(error.getHttpStatusCode(), message);
//    }
}