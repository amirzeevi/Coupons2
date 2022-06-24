package com.couponsSystemPhase2.service;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Company;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.exception.CouponException;
import com.couponsSystemPhase2.exception.NotFoundException;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * A service class for the Company user that holds the business logic layer.
 */
@Service
public class CompanyService extends ClientService {

    private int companyID;

    /**
     * Returns true if user email and password match to database and initialize the company id. else, will return false.
     */
    @Override
    public boolean login(String email, String password) {
        this.companyID = companyRepo.getCompanyId(email, password).orElse(0);
        return this.companyID != 0;
    }

    /**
     * Adds a new coupon to the database.
     */
    public int addCoupon(Coupon coupon) {
        checkCouponData(coupon);

        if (coupon.getId() != 0) {
            throw new CouponException("You can not insert id");
        }
        if (couponRepo.existsByTitleAndCompanyID(coupon.getTitle(), this.companyID)) {
            throw new CouponException("You already own this coupon");
        }
        coupon.setCompanyID(this.companyID);
        System.out.println("Coupon added");
        return couponRepo.saveAndFlush(coupon).getId();
    }

    /**
     * Updates an existing coupon to the database.
     */
    public void updateCoupon(Coupon coupon) {
        checkCouponData(coupon);

        if (!couponRepo.existsById(coupon.getId())) {
            throw new NotFoundException("Coupon not found");
        }
        if (couponRepo.existsByTitleAndCompanyIDAndIdNot(coupon.getTitle(), this.companyID, coupon.getId())) {
            throw new CouponException("Coupon title already exists");
        }
        coupon.setCompanyID(this.companyID);
        couponRepo.saveAndFlush(coupon);
        System.out.println("Coupon updated");
    }

    /**
     * Deletes a coupon from database. If id is not found, throws exception.
     */
    public void deleteCoupon(int couponID) {
        if (!couponRepo.existsByIdAndCompanyID(couponID, companyID)) {
            throw new NotFoundException("Coupon not found");
        }
        couponRepo.deleteById(couponID);
        System.out.println("Coupon deleted");
    }

    /**
     * Returns a list of all company coupons.
     */
    public List<Coupon> getCompanyCoupons() {
        return couponRepo.findAllByCompanyID(companyID);
    }

    /**
     * Returns a list of all company coupons by the specified category.
     */
    public List<Coupon> getCompanyCoupons(Category category) {
        return couponRepo.findAllByCompanyIDAndCategory(companyID, category);
    }

    /**
     * Returns a list of all company coupons by the specified max price.
     */
    public List<Coupon> getCompanyCoupons(double maxPrice) {
        return couponRepo.findAllByCompanyIDAndPriceLessThanEqual(companyID, maxPrice);
    }

    /**
     * Returns this company details
     */
    public Company getCompanyDetails() {
        return companyRepo.findById(this.companyID).get();
    }

    /**
     * Private method to check the coupon data before even trying to access database.
     */
    private void checkCouponData(Coupon coupon) {
//        if (coupon.getCompanyID() != this.companyID) {
//            throw new CouponException("Coupon company id incorrect");
//        }
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
