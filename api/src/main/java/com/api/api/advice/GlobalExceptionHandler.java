package com.api.api.advice;

import com.common.common.exception.MessageConstants;
import com.common.common.exception.dto.ErrorResponse;
import com.common.common.exception.model.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 400 BAD_REQUEST
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    protected ErrorResponse handleBindException(final BindException e) {
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        return ErrorResponse.of(400, MessageConstants.BAD_REQUEST.getMessage(),
                String.format("%s는 %s", fieldError.getField(), fieldError.getDefaultMessage()));
    }

    /**
     * 500 Internal Server
     */

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleException(final Exception e) {
        logger.error("{} : {}", e.getClass(), e.getMessage());
        return ErrorResponse.of(500, e.getMessage());
    }

    /**
     * Custom error
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(ErrorResponse.of(e.getStatusCode(), e.getMessage()));
    }

}
