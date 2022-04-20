package com.couponsSystemPhase2.service;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Company;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.exception.CouponException;
import com.couponsSystemPhase2.exception.NotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Scope(value = "prototype")
public class CompanyService extends ClientService {

    private int companyID;

    @Override
    public boolean login(String email, String password) {
        this.companyID = companyRepo.getCompanyId(email, password).orElse(0);
        return this.companyID != 0;
    }

    public void addCoupon(Coupon coupon) {
        checkCouponData(coupon);

        if (coupon.getId() != 0) {
            throw new CouponException("You can not insert id");
        }
        if (couponRepo.existsByTitleAndCompanyID(coupon.getTitle(), this.companyID)) {
            throw new CouponException("You already own this coupon");
        }
        couponRepo.saveAndFlush(coupon);
        System.out.println("Coupon added");
    }

    public void updateCoupon(Coupon coupon) {
        checkCouponData(coupon);

        if (!(couponRepo.existsById(coupon.getId()))) {
            throw new NotFoundException("Coupon not found");
        }
        if (couponRepo.existsByTitleAndCompanyIDAndIdNot(coupon.getTitle(), this.companyID, coupon.getId())) {
            throw new CouponException("Coupon title already exists");
        }
        couponRepo.saveAndFlush(coupon);
        System.out.println("Coupon updated");
    }

    public void deleteCoupon(int couponID) {
        if (!couponRepo.existsByIdAndCompanyID(couponID, companyID)) {
            throw new NotFoundException("Coupon not found");
        }
        couponRepo.deleteById(couponID);
        System.out.println("Coupon deleted");
    }

    public List<Coupon> getCompanyCoupons() {
        return couponRepo.findAllByCompanyID(companyID);
    }

    public List<Coupon> getCompanyCoupons(Category category) {
        return couponRepo.findAllByCompanyIDAndCategory(companyID, category);
    }

    public List<Coupon> getCompanyCoupons(double maxPrice) {
        return couponRepo.findAllByCompanyIDAndPriceLessThanEqual(companyID, maxPrice);
    }

    public Company getCompanyDetails() {
        return companyRepo.findById(this.companyID).get();
    }

    private void checkCouponData(Coupon coupon) {
        if (coupon.getCompanyID() != this.companyID) {
            throw new CouponException("Coupon company id incorrect");
        }
        if (coupon.getStartDate().after(coupon.getEndDate()) ||
                coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponException("Coupon date incorrect");
        }
        if (coupon.getPrice() < 1) {
            throw new CouponException("Coupon price should be positive");
        }
        if (coupon.getAmount() < 1) {
            throw new CouponException("Coupon amount should be positive");
        }
    }
}
