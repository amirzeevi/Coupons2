package com.couponsSystemPhase2.jobs;

import com.couponsSystemPhase2.repositories.CouponRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * A class that runs a separate thread for daily deleting expired coupons from database.
 */
@Component
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
public class DailyCouponExpired {
    private final CouponRepo couponRepo;

    /**
     * Performs a deletion of expired coupons at midnight every day.
     */
    @Async
    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Jerusalem")
    public void deleteExpiredCoupons() {
        couponRepo.deleteExpiredCoupons();
    }
}
