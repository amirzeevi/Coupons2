package com.couponsSystemPhase2.jobs;

import com.couponsSystemPhase2.repositories.CouponRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
public class DailyCouponExpired {
    private final CouponRepo couponRepo;

    @Async
    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Jerusalem")
    public void deleteExpiredCoupons() {
        couponRepo.deleteExpiredCoupons();
    }
}
