package com.couponsSystemPhase2.controller;


import com.couponsSystemPhase2.security.JWTUtils;
import com.couponsSystemPhase2.service.AdminService;
import com.couponsSystemPhase2.service.ClientService;
import com.couponsSystemPhase2.service.LoginManager;
import com.couponsSystemPhase2.utils.ServiceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loginManager")
@RequiredArgsConstructor
public class LoginManagerController {

    private final LoginManager loginManager;
    private final JWTUtils jwtUtils;
    private final ServiceProvider serviceProvider;

    @PostMapping("/login")
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
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
