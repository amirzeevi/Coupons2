package com.couponsSystemPhase2.controller;

import com.couponsSystemPhase2.beans.Company;
import com.couponsSystemPhase2.beans.Customer;
import com.couponsSystemPhase2.security.JWTUtils;
import com.couponsSystemPhase2.service.AdminService;
import io.swagger.annotations.ResponseHeader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This is the Admin controller class that acts as the 'bridge' between the 'view' and the 'model'.
 * It implements all the AdminService methods and returns the user a new Jwt token with every response.
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final JWTUtils jwtUtils;

    /**
     * A post request to add a new company. returns a new Jwt Token in response body.
     */
    @PostMapping("/company")
    public ResponseEntity<?> addCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) {
        adminService.addCompany(company);
        return ResponseEntity.ok().header("Authorization", jwtUtils.generateToken(token)).build();
    }

    /**
     * A put request to update an existing company. returns a new Jwt Token in response body.
     */
    @PutMapping("/company")
    public ResponseEntity<?> updateCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) {
        adminService.updateCompany(company);
        return ResponseEntity.ok().header("Authorization", jwtUtils.generateToken(token)).build();
    }

    /**
     * A delete request to delete an existing company. returns a new Jwt Token in response body.
     */
    @DeleteMapping("/company/{companyID}")
    public ResponseEntity<?> deleteCompany(@RequestHeader("Authorization") String token, @PathVariable int companyID) {
        adminService.deleteCompany(companyID);
        return ResponseEntity.ok().header("Authorization",jwtUtils.generateToken(token)).build();
    }

    /**
     * A get request for all companies in the system. returns a list of all companies and a new Jwt Token in response header.
     */
    @GetMapping("/company/all")
    public ResponseEntity<?> getAllCompanies(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtils.generateToken(token))
                .body(adminService.getAllCompanies());
    }

    /**
     * A get request for a specific company in the system.
     * returns the specified company if exists and a new Jwt Token in response header.
     */
    @GetMapping("/company/{companyID}")
    public ResponseEntity<?> getOneCompany(@RequestHeader("Authorization") String token, @PathVariable int companyID) {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtils.generateToken(token))
                .body(adminService.getOneCompany(companyID));
    }

    /**
     * A post request to add a new customer. returns a new Jwt Token in response body.
     */
    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(
            @RequestHeader("Authorization") String token, @RequestBody Customer customer) {
        adminService.addCustomer(customer);
        return ResponseEntity.ok().header("Authorization",jwtUtils.generateToken(token)).build();
    }

    /**
     * A put request to update an existing customer. returns a new Jwt Token in response body.
     */
    @PutMapping("/customer")
    public ResponseEntity<?> updateCustomer(
            @RequestHeader("Authorization") String token, @RequestBody Customer customer) {
        adminService.updateCustomer(customer);
        return ResponseEntity.ok().header("Authorization",jwtUtils.generateToken(token)).build();
    }

    /**
     * A delete request to delete an existing customer. returns a new Jwt Token in response body.
     */
    @DeleteMapping("/customer/{customerID}")
    public ResponseEntity<?> deleteCustomer(@RequestHeader("Authorization") String token, @PathVariable int customerID) {
        adminService.deleteCustomer(customerID);
        return ResponseEntity.ok().header("Authorization",jwtUtils.generateToken(token)).build();
    }

    /**
     * A get request for all customer in the system. returns a list of all customer and a new Jwt Token in response header.
     */
    @GetMapping("/customer/{customerID}")
    public ResponseEntity<?> getOneCustomer(@RequestHeader("Authorization") String token, @PathVariable int customerID) {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtils.generateToken(token))
                .body(adminService.getOneCustomer(customerID));
    }

    /**
     * A get request for a single customer by id. returns the specified customer and a new Jwt Token in response header.
     */
    @GetMapping("customer/all")
    public ResponseEntity<?> getAllCustomers(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtils.generateToken(token))
                .body(adminService.getAllCustomers());
    }
}
