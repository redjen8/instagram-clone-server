package com.redjen.instagram.config;

import com.redjen.instagram.domain.common.CustomException;
import com.redjen.instagram.domain.common.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<ErrorResponse> handleDataException(CustomException exception) {
        log.error("CustomException : {}", exception.getErrorCode());
        return ErrorResponse.toResponseEntity(exception.getErrorCode());
    }
}
