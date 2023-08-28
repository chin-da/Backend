package com.api.api.advice;

import com.common.common.exception.ErrorCode;
import com.common.common.exception.dto.ErrorResponse;
import com.common.common.exception.model.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 500 Internal Server
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ErrorResponse handleException(final Exception e) {
        logger.error("{} : {}", e.getClass(), e.getMessage());
        return ErrorResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * Custom error
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleSoptException(CustomException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(ErrorResponse.error(e.getError(), e.getMessage()));
    }

}