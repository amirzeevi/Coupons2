package com.couponsSystemPhase2.controller;


import com.couponsSystemPhase2.security.JWTUtils;
import com.couponsSystemPhase2.service.AdminService;
import com.couponsSystemPhase2.service.ClientService;
import com.couponsSystemPhase2.service.LoginManager;
import com.couponsSystemPhase2.utils.ServiceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A controller class for the login manager component. will issue a Jwt token if a client service is made.
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class LoginManagerController {

    private final LoginManager loginManager;
    private final JWTUtils jwtUtils;
    private final ServiceProvider serviceProvider;

    /**
     * Passes the login manager class the user credentials. Issues a Jwt token and stores the client service
     * attached with their token for the different Company and Customer users.
     */
    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<?> login(@RequestBody UserDetails userDetails) {
        ClientService service = loginManager.login(
                userDetails.getEmail(),
                userDetails.getPassword(),
                userDetails.getClientType());
        String token = jwtUtils.generateToken(
                userDetails.getEmail(),
                userDetails.getClientType());
        if (!(service instanceof AdminService)) {
            serviceProvider.addService(token, service);
        }
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
}
