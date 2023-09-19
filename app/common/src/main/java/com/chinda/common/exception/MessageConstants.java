package com.chinda.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MessageConstants {

    /**
     * 400 BAD REQUEST
     */
    BAD_REQUEST("잘못된 요청입니다."),


    /**
     * 401 UNAUTHORIZED
     */
    UNAUTHORIZED("권한이 없는 사용자입니다."),

    /**
     * 404 RESOURCE NOT FOUND
     */
    RESOURCE_NOT_FOUND("존재하지 않는 데이터입니다."),

    ;

    private final String message;
}
