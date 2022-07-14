package com.couponsSystemPhase2.config;

import com.couponsSystemPhase2.interceptor.ControllerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.couponsSystemPhase2.service.ClientType.*; 

/**
 * This is a Configuration class that adds Spring HandlerInterceptors into to the Spring Web Mvc configuration,
 * Specifying the different paths for the different controllers so that each request to a handler will be
 * intercepted by the interceptor in order to validate the request's Jwt Token as well as the user authorizes.
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final ControllerInterceptor adminInterceptor;
    private final ControllerInterceptor companyInterceptor;
    private final ControllerInterceptor customerInterceptor;

    /**
     * All args constructor that will set the interceptors with the specific client type for them.
     */
    public InterceptorConfig(ControllerInterceptor adminInterceptor,
                             ControllerInterceptor companyInterceptor,
                             ControllerInterceptor customerInterceptor){

        this.adminInterceptor = adminInterceptor;
        adminInterceptor.clientType = ADMINISTRATOR;
        this.companyInterceptor = companyInterceptor;
        companyInterceptor.clientType = COMPANY;
        this.customerInterceptor = customerInterceptor;
        customerInterceptor.clientType = CUSTOMER;
    }

    /**
     * This method is overridden from Spring Web Mvc Configurer to add the
     * interceptors to their marching path for controllers classes.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");
        registry.addInterceptor(companyInterceptor).addPathPatterns("/company/**");
        registry.addInterceptor(customerInterceptor).addPathPatterns("/customer/**");
    }
}
