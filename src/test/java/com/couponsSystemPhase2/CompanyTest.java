package com.couponsSystemPhase2;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.service.ClientType;
import com.couponsSystemPhase2.service.CompanyService;
import com.couponsSystemPhase2.service.LoginManager;
import com.couponsSystemPhase2.utils.TablePrinter;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class CompanyTest {

    CompanyService companyService;
    @Autowired
    LoginManager loginManager;

    @BeforeEach
    void setUp() {
        companyService = (CompanyService) loginManager.login("new.email@com", "1234", ClientType.COMPANY);
    }

    @Test
    @Order(1)
    public void loginFail() {
        try {
            System.out.println("TESTING COMPANY LOGIN FAIL");
            loginManager.login("ascas", "vfsv", ClientType.COMPANY);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(2)
    public void loginTest() {
        System.out.println("TESTING COMPANY LOGIN");
        companyService = (CompanyService) loginManager.login("new.email@com", "1234", ClientType.COMPANY);
    }

    @Test
    @Order(3)
    public void addCouponTest() {
        Coupon coupon1 = Coupon
                .builder()
                .companyID(1)
                .category(Category.HOLIDAY)
                .title("Pants")
                .description("discount on pants for xmes")
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
    }

    @Test
    @Order(4)
    public void addCouponFail() {
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
        try {
            System.out.println("TESTING ADD COUPON FAIL");
            companyService.addCoupon(coupon1);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void updateCouponTest() {
        System.out.println("TESTING UPDATE COUPON");
        Coupon couponFromDB = companyService.getCompanyCoupons().get(0);
        couponFromDB.setPrice(199.99);
        companyService.updateCoupon(couponFromDB);
    }

    @Test
    @Order(6)
    public void updateCouponFail() {
        try {
            System.out.println("TESTING UPDATE COUPON FAIL");
            Coupon couponFromDB = companyService.getCompanyCoupons().get(0);
            couponFromDB.setEndDate(Date.valueOf(LocalDate.now().minusDays(20)));
            companyService.updateCoupon(couponFromDB);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void deleteCouponTest() {
        System.out.println("TESTING DELETE COUPON");
        companyService.deleteCoupon(3);
    }

    @Test
    @Order(8)
    public void deleteCouponFail() {
        try {
            System.out.println("TESTING DELETE COUPON FAIL");
            companyService.deleteCoupon(3);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(9)
    public void getCompanyCouponsTest() {
        System.out.println("TESTING GET COMPANY COUPONS");
        TablePrinter.print(companyService.getCompanyCoupons());
    }

    @Test
    @Order(10)
    public void getCompanyCouponsCategoryTest() {
        System.out.println("TESTING GET COMPANY COUPONS BY CATEGORY");
        TablePrinter.print(companyService.getCompanyCoupons(Category.HOLIDAY));
    }

    @Test
    @Order(11)
    public void getCompanyCouponsMaxPriceTest() {
        System.out.println("TESTING GET COMPANY COUPONS BY MAX PRICE");
        TablePrinter.print(companyService.getCompanyCoupons(100));
    }

    @Test
    @Order(12)
    public void getCompanyDetails() {
        System.out.println("TESTING GET COMPANY DETAILS");
        TablePrinter.print(companyService.getCompanyDetails());
    }
}