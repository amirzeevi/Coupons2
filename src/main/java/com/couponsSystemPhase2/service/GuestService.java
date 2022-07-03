package com.couponsSystemPhase2.service;

import com.couponsSystemPhase2.beans.Company;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.repositories.CompanyRepo;
import com.couponsSystemPhase2.repositories.CouponRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final CouponRepo couponRepo;
    private final CompanyRepo companyRepo;

    public List<Coupon> getAllCoupons(){
        return couponRepo.findAll();
    }

    public List<Company> getAllCompanies(){
        List<Company> companies = companyRepo.findAll();
        companies.forEach(company -> company.setPassword(""));
        return companies;
    }
}
