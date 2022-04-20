package com.couponsSystemPhase2.controller;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.exception.TokenException;
import com.couponsSystemPhase2.service.CustomerService;
import com.couponsSystemPhase2.utils.ServiceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final ServiceProvider serviceProvider;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseCoupon(@RequestHeader("Authorization") String token, @RequestBody Coupon coupon) {
        getCustomerService(token).purchaseCoupon(coupon);
        return new ResponseEntity<>(refreshToken(token), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader("Authorization") String token) {
        CustomerService customerService = getCustomerService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(customerService.getCustomerCoupons());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getCustomerCouponsByCategory(@RequestHeader("Authorization") String token, @PathVariable Category category) {
        CustomerService customerService = getCustomerService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(customerService.getCustomerCoupons(category));
    }

    @GetMapping("/maxPrice/{maxPrice}")
    public ResponseEntity<?> getCustomerCouponsByMaxPrice(@RequestHeader("Authorization") String token, @PathVariable double maxPrice) {
        CustomerService customerService = getCustomerService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(customerService.getCustomerCoupons(maxPrice));
    }

    @GetMapping("/details")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader("Authorization") String token) {
        CustomerService customerService = getCustomerService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(customerService.getCustomerDetails());
    }

    private CustomerService getCustomerService(String token) {
        return (CustomerService) Optional.ofNullable(serviceProvider.getServices().get(token)).
                orElseThrow(() -> new TokenException("Token is not correct. Please log in again"));
    }

    private String refreshToken(String token) {
        return serviceProvider.refreshToken(token);
    }
}
