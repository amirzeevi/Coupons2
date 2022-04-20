package com.couponsSystemPhase2.service;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.beans.Customer;
import com.couponsSystemPhase2.exception.CouponException;
import com.couponsSystemPhase2.exception.NotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
public class CustomerService extends ClientService {

    private int customerID;

    @Override
    public boolean login(String email, String password) {
        this.customerID = customerRepo.getCustomerId(email, password).orElse(0);
        return customerID != 0;
    }

    public void purchaseCoupon(Coupon coupon) {
        Coupon couponToPurchase = couponRepo.findById(coupon.getId()).
                orElseThrow(() -> new NotFoundException("Coupon not found"));

        if (couponToPurchase.getAmount() == 0) {
            throw new CouponException("Coupon is out of stock");
        }
        if (couponRepo.isPurchaseExist(coupon.getId(), customerID) == 1) {
            throw new CouponException("You already own this coupon");
        }
        couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
        couponRepo.addCouponPurchase(coupon.getId(), customerID);
        couponRepo.saveAndFlush(couponToPurchase); /// TODO: 15/04/2022 find a way to automate this
    }

    public List<Coupon> getCustomerCoupons() {
        return couponRepo.getCustomerCoupons(this.customerID);
    }

    public List<Coupon> getCustomerCoupons(double maxPrice) {
        return couponRepo.getCustomerCouponsByMaxPrice(this.customerID, maxPrice);
    }

    public List<Coupon> getCustomerCoupons(Category category) {
        return couponRepo.getCustomerCouponsByCategory(this.customerID, category.ordinal());
    }

    public Customer getCustomerDetails() {
        return customerRepo.findById(this.customerID).get();
    }
}
