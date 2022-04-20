package com.couponsSystemPhase2.config;

import com.couponsSystemPhase2.interceptor.ServiceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.couponsSystemPhase2.service.ClientType.*;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final ServiceInterceptor adminInterceptor;
    private final ServiceInterceptor companyInterceptor;
    private final ServiceInterceptor customerInterceptor;

    public InterceptorConfig(ServiceInterceptor adminInterceptor,
                             ServiceInterceptor companyInterceptor,
                             ServiceInterceptor customerInterceptor){

        this.adminInterceptor = adminInterceptor;
        adminInterceptor.clientType = ADMINISTRATOR;
        this.companyInterceptor = companyInterceptor;
        companyInterceptor.clientType = COMPANY;
        this.customerInterceptor = customerInterceptor;
        customerInterceptor.clientType = CUSTOMER;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");
        registry.addInterceptor(companyInterceptor).addPathPatterns("/company/**");
        registry.addInterceptor(customerInterceptor).addPathPatterns("/customer/**");
    }
}
