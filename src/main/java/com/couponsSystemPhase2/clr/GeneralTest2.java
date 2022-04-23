package com.couponsSystemPhase2.clr;

import com.couponsSystemPhase2.repositories.CouponRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

//@Component
@Order(2)
@RequiredArgsConstructor
public class GeneralTest2 implements CommandLineRunner {
    private final CouponRepo couponRepo;

    @Override
    public void run(String... args) throws Exception {
    }
}
