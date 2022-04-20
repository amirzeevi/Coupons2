package com.couponsSystemPhase2.clr;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.repositories.CouponRepo;
import com.couponsSystemPhase2.service.CompanyService;
import com.couponsSystemPhase2.service.CustomerService;
import com.couponsSystemPhase2.utils.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@Order(3)
@RequiredArgsConstructor
public class customerTest implements CommandLineRunner {
    private final CustomerService customerService;
    private final CouponRepo couponRepo;

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("TESTING CUSTOMER LOGIN FAIL");
            customerService.login("vfvs", "xcvxcv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING CUSTOMER LOGIN");
        customerService.login("new@com", "1234");
        System.out.println();

        System.out.println("TESTING CUSTOMER COUPON PURCHASE");
        List<Coupon> couponsFromDB = couponRepo.findAll();
        customerService.purchaseCoupon(couponsFromDB.get(0));
        customerService.purchaseCoupon(couponsFromDB.get(1));
        System.out.println();

        try {
            System.out.println("TESTING CUSTOMER COUPON PURCHASE FAIL");
            customerService.purchaseCoupon(couponsFromDB.get(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING GET CUSTOMER COUPONS");
        TablePrinter.print(customerService.getCustomerCoupons());
        System.out.println();

        System.out.println("TESTING GET CUSTOMER COUPONS BY CATEGORY");
        TablePrinter.print(customerService.getCustomerCoupons(Category.HOLIDAY));
        System.out.println();

        System.out.println("TESTING GET CUSTOMER COUPONS BY MAX PRICE");
        TablePrinter.print(customerService.getCustomerCoupons(100));
        System.out.println();

        System.out.println("TESTING GET CUSTOMER DETAILS");
        TablePrinter.print(customerService.getCustomerDetails());
    }
}
