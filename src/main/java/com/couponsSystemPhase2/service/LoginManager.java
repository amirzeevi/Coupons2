package com.couponsSystemPhase2.service;

import com.couponsSystemPhase2.exception.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

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
    public ClientService login(String email, String password, ClientType clientType) {
        ClientService clientService = ctx.getBean(clientType.serviceClass);
        if (clientService.login(email, password)) {
            return clientService;
        }
        throw new LoginException("Email or password incorrect");
    }
}
