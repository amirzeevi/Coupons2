package com.couponsSystemPhase2.clr;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.controller.UserDetails;
import com.couponsSystemPhase2.service.ClientType;
import com.couponsSystemPhase2.service.CompanyService;
import com.couponsSystemPhase2.service.LoginManager;
import com.couponsSystemPhase2.utils.TablePrinter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import java.sql.Date;
import java.time.LocalDate;
/**
 * This is the test class for the CompanyService class.
 * Implementing all the class's methods and testing them.
 */

@Component
@Order(2)
@RequiredArgsConstructor
public class CompanyTest implements CommandLineRunner {

    private final CompanyService companyService;
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {

        try {
            System.out.println("TESTING COMPANY LOGIN FAIL");
            UserDetails userDetails = new UserDetails("ascas", "vfsv", ClientType.COMPANY);
            loginManager.login(userDetails);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("TESTING COMPANY LOGIN");
        companyService.login("new.email@com", "1234");
        System.out.println();

        Coupon coupon1 = Coupon
                .builder()
                .companyID(1)
                .category(Category.FASHION)
                .title("Men's Sweaters")
                .description("60% off Wool Fair Isle Wool Sweater")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .amount(20)
                .price(99.99)
                .image("https://cache.mrporter.com/variants/images/27086482324270201/in/w960_q60.jpg")
                .build();

        Coupon coupon2 = Coupon
                .builder()
                .companyID(1)
                .category(Category.FASHION)
                .title("Men's Pants")
                .description("50% discount Brown Men's Casual Pant")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .amount(20)
                .price(99.99)
                .image("https://4.imimg.com/data4/DJ/KV/MY-12091495/men-s-cotton-pant-500x500.jpg")
                .build();

        Coupon coupon3 = Coupon
                .builder()
                .companyID(1)
                .category(Category.FASHION)
                .title("Socks")
                .description("30% off 1 Pair Fashion Novelty Socks Colorful")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .amount(20)
                .price(9.99)
                .image("https://litb-cgis.rightinthebox.com/images/640x640/202107/bps/product/inc/xmsghg1625215561641.jpg")
                .build();

        Coupon coupon4 = Coupon
                .builder()
                .companyID(1)
                .category(Category.FASHION)
                .title("Men's T-Shirt")
                .description("60% off Men's Crew Neck Cotton T-Shirt")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .amount(20)
                .price(9.99)
                .image("https://d3uvb2lhumlp.cloudfront.net/pub/media/catalog/product/cache/f751fae08115cd00f88855057a9f034e/t/h/th7391_9nx_24_1.jpg")
                .build();

        Coupon coupon5 = Coupon
                .builder()
                .companyID(1)
                .category(Category.FASHION)
                .title("destory")
                .description(" ")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .amount(20)
                .price(9.99)
                .image(" ")
                .build();

        System.out.println("TESTING ADD COUPON");
        companyService.addCoupon(coupon1);
        companyService.addCoupon(coupon2);
        companyService.addCoupon(coupon3);
        companyService.addCoupon(coupon4);
        companyService.addCoupon(coupon5);
        System.out.println();

        try {
            System.out.println("TESTING ADD COUPON FAIL");
            companyService.addCoupon(coupon1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING UPDATE COUPON");
        Coupon couponFromDB = companyService.getCompanyCoupons().get(4);
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
        companyService.deleteCoupon(5);
        System.out.println();

        try {
            System.out.println("TESTING DELETE COUPON FAIL");
            companyService.deleteCoupon(5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING GET COMPANY COUPONS");
        TablePrinter.print(companyService.getCompanyCoupons());
        System.out.println();


        System.out.println("TESTING GET COMPANY COUPONS BY CATEGORY");
        TablePrinter.print(companyService.getCompanyCoupons(Category.FASHION));
        System.out.println();


        System.out.println("TESTING GET COMPANY COUPONS BY MAX PRICE");
        TablePrinter.print(companyService.getCompanyCoupons(100));
        System.out.println();

        System.out.println("TESTING GET COMPANY DETAILS");
        TablePrinter.print(companyService.getCompanyDetails());
        System.out.println();
    }
}
