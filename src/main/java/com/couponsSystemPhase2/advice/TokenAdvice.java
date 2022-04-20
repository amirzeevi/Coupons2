package com.couponsSystemPhase2.advice;

import com.couponsSystemPhase2.exception.TokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TokenAdvice {
    @ExceptionHandler(value = {MalformedJwtException.class, TokenException.class, ExpiredJwtException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetail tokenException (Exception e){
        return new ErrorDetail("Token Error", e.getMessage());
    }
}
