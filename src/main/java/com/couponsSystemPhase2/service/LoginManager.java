package com.couponsSystemPhase2.service;

import com.couponsSystemPhase2.controller.UserDetails;
import com.couponsSystemPhase2.exception.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * A login manager class that will return the different types of users their service classes.
 */
@Component
public class LoginManager {

    @Autowired
    ApplicationContext ctx;

    /*
        public ClientService login(String email, String password, ClientType clientType) {
        switch (clientType) {
            case ADMINISTRATOR:
                clientService = ctx.getBean(AdminService.class);
                break;
            case COMPANY:
                clientService = ctx.getBean(CompanyService.class);
                break;
            case CUSTOMER:
                clientService = ctx.getBean(CustomerService.class);
                break;
        }
        if (clientService.login(email, password)) {
            return clientService;
        }
        throw new CouponException("Email or password incorrect");
    }
    */

    /**
     * A method for a user to login. Returns the user service class if email and password match,
     * Else throws a login exception.
     */
    public ClientService login(UserDetails userDetails) {
        ClientService clientService = ctx.getBean(userDetails.getClientType().serviceClass);
        if (clientService.login(userDetails.getEmail(), userDetails.getPassword())) {
            return clientService;
        }
        throw new LoginException("Email or password incorrect");
    }
}
