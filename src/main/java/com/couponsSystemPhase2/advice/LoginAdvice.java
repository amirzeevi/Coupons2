package com.couponsSystemPhase2.advice;

import com.couponsSystemPhase2.exception.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LoginAdvice {
    @ExceptionHandler(value = LoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail LoginException(Exception e) {
        return new ErrorDetail("Login Error", e.getMessage());
    }
}
