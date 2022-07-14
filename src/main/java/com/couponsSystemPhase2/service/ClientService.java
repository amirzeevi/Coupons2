package com.couponsSystemPhase2.service;

import com.couponsSystemPhase2.repositories.CompanyRepo;
import com.couponsSystemPhase2.repositories.CouponRepo;
import com.couponsSystemPhase2.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired; 

public abstract class ClientService {

    @Autowired
    protected CouponRepo couponRepo;
    @Autowired
    protected CompanyRepo companyRepo;
    @Autowired
    protected CustomerRepo customerRepo;


    public abstract boolean login(String email, String password);
}
