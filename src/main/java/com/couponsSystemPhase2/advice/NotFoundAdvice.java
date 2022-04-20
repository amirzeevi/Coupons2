package com.couponsSystemPhase2.advice;

import com.couponsSystemPhase2.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundAdvice {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail notFoundException(Exception e){
        return new ErrorDetail("NotFoundError", e.getMessage());
    }
}
