package com.couponsSystemPhase2.clr;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.repositories.CouponRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@Order(2)
@RequiredArgsConstructor
public class generalTest2 implements CommandLineRunner {
    private final CouponRepo couponRepo;

    @Override
    public void run(String... args) throws Exception {
    }
}
