package com.couponsSystemPhase2.service;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.beans.Customer;
import com.couponsSystemPhase2.exception.CouponException;
import com.couponsSystemPhase2.exception.NotFoundException;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A service class for the Customer user that holds the business logic layer.
 */
@Service
public class CustomerService extends ClientService {

    private int customerID;

    /**
     * Returns true if user email and password match to database and initialize the company id. else, will return false.
     */
    @Override
    public boolean login(String email, String password) {
        this.customerID = customerRepo.getCustomerId(email, password).orElse(0);
        return customerID != 0;
    }

    /**
     * A method to add the specified coupon to the customer vs coupon table.
     */
    public void purchaseCoupon(Coupon coupon) {
        Coupon couponToPurchase = couponRepo.findById(coupon.getId()).
                orElseThrow(() -> new NotFoundException("Coupon not found"));

        if (couponToPurchase.getAmount() == 0) {
            throw new CouponException("Coupon is out of stock");
        }
        if (couponRepo.isPurchaseExist(coupon.getId(), customerID) == 1) {
            throw new CouponException("You already own coupon " + coupon.getTitle());
        }
        couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
        couponRepo.addCouponPurchase(coupon.getId(), customerID);
        couponRepo.saveAndFlush(couponToPurchase);
    }

    /**
     * Returns this customer details.
     */
    public List<Coupon> getCustomerCoupons() {
        return couponRepo.getCustomerCoupons(this.customerID);
    }

    /**
     * Returns a list of the customer coupons by the specified category.
     */
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        return couponRepo.getCustomerCouponsByMaxPrice(this.customerID, maxPrice);
    }

    /**
     * Returns a list of the customer coupons by the specified category.
     */
    public List<Coupon> getCustomerCoupons(Category category) {
        return couponRepo.getCustomerCouponsByCategory(this.customerID, category.ordinal());
    }

    /**
     * Returns this customer details.
     */
    public Customer getCustomerDetails() {
        return customerRepo.findById(this.customerID).get();
    }
}
