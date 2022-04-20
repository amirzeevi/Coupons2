package com.couponsSystemPhase2.clr;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.service.CompanyService;
import com.couponsSystemPhase2.utils.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import java.sql.Date;
import java.time.LocalDate;

//@Component
@Order(2)
@RequiredArgsConstructor
public class companyTest implements CommandLineRunner {
    private final CompanyService companyService;

    @Override
    public void run(String... args) throws Exception {

        try {
            System.out.println("TESTING COMPANY LOGIN FAIL");
            companyService.login("ascas", "vfsv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("TESTING COMPANY LOGIN");
        companyService.login("new.email@com", "1234");
        System.out.println();

        Coupon coupon1 = Coupon
                .builder()
                .companyID(1)
                .category(Category.HOLIDAY)
                .title("Pants")
                .description("disecount on pants for xmes")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .amount(20)
                .price(99.99)
                .image("image")
                .build();

        Coupon coupon2 = Coupon
                .builder()
                .companyID(1)
                .category(Category.HOLIDAY)
                .title("Shirts")
                .description("discount on shirts for xmes")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .amount(20)
                .price(99.99)
                .image("image")
                .build();

        Coupon coupon3 = Coupon
                .builder()
                .companyID(1)
                .category(Category.MUSEUM)
                .title("Socks")
                .description("discount on pants if go to a museum")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .amount(20)
                .price(9.99)
                .image("image")
                .build();


        System.out.println("TESTING ADD COUPON");
        companyService.addCoupon(coupon1);
        companyService.addCoupon(coupon2);
        companyService.addCoupon(coupon3);
        System.out.println();

        try {
            System.out.println("TESTING ADD COUPON FAIL");
            companyService.addCoupon(coupon1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING UPDATE COUPON");
        Coupon couponFromDB = companyService.getCompanyCoupons().get(0);
        couponFromDB.setPrice(199.99);
        companyService.updateCoupon(couponFromDB);
        System.out.println();

        try {
            System.out.println("TESTING UPDATE COUPON FAIL");
            couponFromDB.setEndDate(Date.valueOf(LocalDate.now().minusDays(20)));
            companyService.updateCoupon(couponFromDB);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING DELETE COUPON");
        companyService.deleteCoupon(3);
        System.out.println();

        try {
            System.out.println("TESTING DELETE COUPON FAIL");
            companyService.deleteCoupon(3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING GET COMPANY COUPONS");
        TablePrinter.print(companyService.getCompanyCoupons());
        System.out.println();


        System.out.println("TESTING GET COMPANY COUPONS BY CATEGORY");
        TablePrinter.print(companyService.getCompanyCoupons(Category.HOLIDAY));
        System.out.println();


        System.out.println("TESTING GET COMPANY COUPONS BY MAX PRICE");
        TablePrinter.print(companyService.getCompanyCoupons(100));
        System.out.println();

        System.out.println("TESTING GET COMPANY DETAILS");
        TablePrinter.print(companyService.getCompanyDetails());
        System.out.println();


    }
}
