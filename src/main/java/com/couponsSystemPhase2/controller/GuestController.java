package com.couponsSystemPhase2.controller; 

import com.couponsSystemPhase2.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @GetMapping("/coupons")
    public ResponseEntity<?> getAllCoupons() {
        return ResponseEntity.ok(guestService.getAllCoupons());
    }

    @GetMapping("/companies")
    public ResponseEntity<?> Companies() {
        return ResponseEntity.ok(guestService.getAllCompanies());
    }

}
