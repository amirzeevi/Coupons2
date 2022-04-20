package com.couponsSystemPhase2.controller;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.exception.TokenException;
import com.couponsSystemPhase2.service.CompanyService;
import com.couponsSystemPhase2.utils.ServiceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

        private final ServiceProvider serviceProvider;

    @PostMapping("/add")
    public ResponseEntity<?> addCoupon(@RequestHeader("Authorization") String token, @RequestBody Coupon coupon) {
        getCompanyService(token).addCoupon(coupon);
        return new ResponseEntity<>(refreshToken(token), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCoupon(@RequestHeader("Authorization") String token, @RequestBody Coupon coupon) {
        getCompanyService(token).updateCoupon(coupon);
        return new ResponseEntity<>(refreshToken(token), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{couponID}")
    public ResponseEntity<?> deleteCoupon(@RequestHeader("Authorization") String token, @PathVariable int couponID) {
        getCompanyService(token).deleteCoupon(couponID);
        return new ResponseEntity<>(refreshToken(token), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader("Authorization") String token) {
        CompanyService companyService = getCompanyService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(companyService.getCompanyCoupons());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getCompanyCouponsByCategory(@RequestHeader("Authorization") String token, @PathVariable Category category) {
        CompanyService companyService = getCompanyService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(companyService.getCompanyCoupons(category));
    }

    @GetMapping("/maxPrice/{maxPrice}")
    public ResponseEntity<?> getCompanyCouponsByMaxPrice(@RequestHeader("Authorization") String token, @PathVariable double maxPrice) {
        CompanyService companyService = getCompanyService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(companyService.getCompanyCoupons(maxPrice));
    }

    @GetMapping("/details")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader("Authorization") String token) {
        CompanyService companyService = getCompanyService(token);
        return ResponseEntity.ok()
                .header("Authorization", refreshToken(token))
                .body(companyService.getCompanyDetails());
    }

    private CompanyService getCompanyService(String token) {
        return (CompanyService) Optional.ofNullable(serviceProvider.getServices().get(token)).
                orElseThrow(() -> new TokenException("Your token is not correct. Please log in again"));
    }

    private String refreshToken(String token) {
        return serviceProvider.refreshToken(token);
    }
}
