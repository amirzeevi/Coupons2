package com.couponsSystemPhase2.advice;

import com.couponsSystemPhase2.exception.CouponException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponAdvice {

    @ExceptionHandler(value = CouponException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail exceptionHandler(Exception e){
        return new ErrorDetail("Coupon Error", e.getMessage());
    }
}
