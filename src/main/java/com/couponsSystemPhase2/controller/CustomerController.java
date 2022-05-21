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

/**
 * This is the Customer controller class that acts as the 'bridge' between the 'view' and the 'model'.
 * It implements all the CustomerService methods. A Jwt token is requested with every method request
 * for authorization and authentication and a new token is returned in response.
 */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final ServiceProvider serviceProvider;

    /**
     * A post request to add a new customer. returns a new Jwt Token in response body.
     */
    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseCoupon(@RequestHeader("Authorization") String token, @RequestBody Coupon coupon) {
        getCustomerService(token).purchaseCoupon(coupon);
        return new ResponseEntity<>(refreshToken(token), HttpStatus.CREATED);
    }

    /**
     * A get request to return all the customer coupons. returns a list of coupons and a new Jwt Token in response header.
     */
    @GetMapping("/all")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader("Authorization") String token) {
        CustomerService customerService = getCustomerService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(customerService.getCustomerCoupons());
    }

    /**
     * A get request to return all the customer coupons that are from the specified category.
     * returns a list of coupons and a new Jwt Token in response header.
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getCustomerCouponsByCategory(@RequestHeader("Authorization") String token, @PathVariable Category category) {
        CustomerService customerService = getCustomerService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(customerService.getCustomerCoupons(category));
    }

    /**
     * A get request to return all the customer coupons that are from the specified mac price.
     * returns a list of coupons and a new Jwt Token in response header.
     */
    @GetMapping("/maxPrice/{maxPrice}")
    public ResponseEntity<?> getCustomerCouponsByMaxPrice(@RequestHeader("Authorization") String token, @PathVariable double maxPrice) {
        CustomerService customerService = getCustomerService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(customerService.getCustomerCoupons(maxPrice));
    }

    /**
     * A get request to return this customer details from the database.
     * returns the customer details and a new Jwt Token in response header.
     */
    @GetMapping("/details")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader("Authorization") String token) {
        CustomerService customerService = getCustomerService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(customerService.getCustomerDetails());
    }

    /**
     * A private method to provide the Customer Service object to be used in this class requests handles
     * for the specific user based on his token.
     */
    private CustomerService getCustomerService(String token) {
        return (CustomerService) Optional.ofNullable(serviceProvider.getServices().get(token)).
                orElseThrow(() -> new TokenException("Token is not correct. Please log in again"));
    }

    /**
     * Private method that generates a new token to be returned to the user based on their existing token.
     */
    private String refreshToken(String token) {
        return serviceProvider.refreshToken(token);
    }
}
