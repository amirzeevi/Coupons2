package com.couponsSystemPhase2.controller;

import com.couponsSystemPhase2.beans.Company;
import com.couponsSystemPhase2.beans.Customer;
import com.couponsSystemPhase2.security.JWTUtils;
import com.couponsSystemPhase2.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final JWTUtils jwtUtils;

    @PostMapping("/company/add")
    public ResponseEntity<?> addCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) {
        adminService.addCompany(company);
        return new ResponseEntity<>(jwtUtils.generateToken(token), HttpStatus.OK);
    }

    @PutMapping("/company/update")
    public ResponseEntity<?> updateCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) {
        adminService.updateCompany(company);
        return new ResponseEntity<>(jwtUtils.generateToken(token), HttpStatus.OK);
    }

    @DeleteMapping("/company/delete/{companyID}")
    public ResponseEntity<?> deleteCompany(@RequestHeader("Authorization") String token, @PathVariable int companyID) {
        adminService.deleteCompany(companyID);
        return new ResponseEntity<>(jwtUtils.generateToken(token), HttpStatus.OK);
    }

    @GetMapping("company/all")
    public ResponseEntity<?> getAllCompanies(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtils.generateToken(token))
                .body(adminService.getAllCompanies());
    }

    @GetMapping("company/single/{companyID}")
    public ResponseEntity<?> getOneCompany(@RequestHeader("Authorization") String token, @PathVariable int companyID) {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtils.generateToken(token))
                .body(adminService.getOneCompany(companyID));
    }

    @PostMapping("/customer/add")
    public ResponseEntity<?> addCustomer(
            @RequestHeader("Authorization") String token, @RequestBody Customer customer) {
        adminService.addCustomer(customer);
        return new ResponseEntity<>(jwtUtils.generateToken(token), HttpStatus.OK);
    }

    @PutMapping("/customer/update")
    public ResponseEntity<?> updateCustomer(
            @RequestHeader("Authorization") String token, @RequestBody Customer customer) {
        adminService.updateCustomer(customer);
        return new ResponseEntity<>(jwtUtils.generateToken(token), HttpStatus.OK);
    }

    @DeleteMapping("/customer/delete/{customerID}")
    public ResponseEntity<?> deleteCustomer(@RequestHeader("Authorization") String token, @PathVariable int customerID) {
        adminService.deleteCustomer(customerID);
        return new ResponseEntity<>(jwtUtils.generateToken(token), HttpStatus.OK);
    }

    @GetMapping("customer/single/{customerID}")
    public ResponseEntity<?> getOneCustomer(@RequestHeader("Authorization") String token, @PathVariable int customerID) {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtils.generateToken(token))
                .body(adminService.getOneCustomer(customerID));
    }

    @GetMapping("customer/all")
    public ResponseEntity<?> getAllCustomers(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtils.generateToken(token))
                .body(adminService.getAllCustomers());
    }
}
