package com.couponsSystemPhase2.controller;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.exception.TokenException;
import com.couponsSystemPhase2.service.CompanyService;
import com.couponsSystemPhase2.utils.ServiceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * This is the Company controller class that acts as the 'bridge' between the 'view' and the 'model'.
 * It implements all the CompanyService methods. A Jwt token is requested with every method request
 * for authorization and authentication and a new token is returned in response.
 */
@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final ServiceProvider serviceProvider;

    /**
     * A post request to add a new coupon. returns a new Jwt Token in response body.
     */
    @PostMapping("/coupon")
    public ResponseEntity<?> addCoupon(@RequestHeader("Authorization") String token, @RequestBody Coupon coupon) {
        getCompanyService(token).addCoupon(coupon);
        return ResponseEntity.ok().header("Authorization",refreshToken(token)).build();
    }

    /**
     * A put request to update an existing coupon. returns a new Jwt Token in response body.
     */
    @PutMapping("/coupon")
    public ResponseEntity<?> updateCoupon(@RequestHeader("Authorization") String token, @RequestBody Coupon coupon) {
        getCompanyService(token).updateCoupon(coupon);
        return ResponseEntity.ok().header("Authorization",refreshToken(token)).build();
    }

    /**
     * A delete request to delete an existing coupon. returns a new Jwt Token in response body.
     */
    @DeleteMapping("/coupon/{couponID}")
    public ResponseEntity<?> deleteCoupon(@RequestHeader("Authorization") String token, @PathVariable int couponID) {
        getCompanyService(token).deleteCoupon(couponID);
        return ResponseEntity.ok().header("Authorization",refreshToken(token)).build();
    }

    /**
     * A get request for all the company coupons in the system. returns a list of all coupons and a new Jwt Token in response header.
     */
    @GetMapping("/coupon")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader("Authorization") String token) {
        CompanyService companyService = getCompanyService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(companyService.getCompanyCoupons());
    }

    /**
     * A get request for all the company coupons that are of the specified category.
     * returns al list of coupons and a new Jwt Token in response header.
     */
    @GetMapping("/coupons/category/{category}")
    public ResponseEntity<?> getCompanyCouponsByCategory(@RequestHeader("Authorization") String token, @PathVariable Category category) {
        CompanyService companyService = getCompanyService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(companyService.getCompanyCoupons(category));
    }

    /**
     * A get request for all coupons in the system that are of the specified max price.
     * returns a list of coupons and a new Jwt Token in response header.
     */
    @GetMapping("/coupons/maxPrice/{maxPrice}")
    public ResponseEntity<?> getCompanyCouponsByMaxPrice(@RequestHeader("Authorization") String token, @PathVariable double maxPrice) {
        CompanyService companyService = getCompanyService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(companyService.getCompanyCoupons(maxPrice));
    }

    /**
     * A get request to return this company details from the database.
     * returns the company details and a new Jwt Token in response header.
     */
    @GetMapping("/details")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader("Authorization") String token) {
        CompanyService companyService = getCompanyService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(companyService.getCompanyDetails());
    }

    /**
     * A private method to provide the Company Service object to be used in this class requests handles
     * for the specific user based on his token.
     */
    private CompanyService getCompanyService(String token) {
        return (CompanyService) Optional.ofNullable(serviceProvider.getServices().get(token)).
                orElseThrow(() -> new TokenException("Your token is not correct. Please log in again"));
    }

    /**
     * Private method that generates a new token to be returned to the user based on their existing token.
     */
    private String refreshToken(String token) {
        return serviceProvider.refreshToken(token);
    }
}
