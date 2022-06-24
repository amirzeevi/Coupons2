package com.couponsSystemPhase2.controller;


import com.couponsSystemPhase2.security.JWTUtils;
import com.couponsSystemPhase2.service.AdminService;
import com.couponsSystemPhase2.service.ClientService;
import com.couponsSystemPhase2.service.LoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller class for the login manager component. will issue a Jwt token if a client service is made.
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class LoginController {

    private final LoginManager loginManager;
    private final JWTUtils jwtUtils;


    /**
     * Passes the login manager class the user credentials. Issues a Jwt token.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDetails userDetails) {
        loginManager.login(userDetails);
        String token = jwtUtils.generateToken(userDetails.getEmail(), userDetails.getClientType());
        return ResponseEntity.ok().header("Authorization", token).build();
    }
}
