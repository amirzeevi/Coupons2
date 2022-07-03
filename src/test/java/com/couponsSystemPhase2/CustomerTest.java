package com.couponsSystemPhase2; 

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.controller.UserDetails;
import com.couponsSystemPhase2.repositories.CouponRepo;
import com.couponsSystemPhase2.service.ClientType;
import com.couponsSystemPhase2.service.CustomerService;
import com.couponsSystemPhase2.service.LoginManager;
import com.couponsSystemPhase2.utils.TablePrinter;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
/**
 * Please exclude clr package testing before using junit
 */
@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class CustomerTest {

    CustomerService customerService;
    @Autowired
    LoginManager loginManager;
    @Autowired
    CouponRepo couponRepo;

    @BeforeEach
    void setUp() {
        UserDetails userDetails = new UserDetails("john2@com", "1234", ClientType.CUSTOMER);
        customerService = (CustomerService) loginManager.login(userDetails);
    }

    @Test
    @Order(1)
    public void loginFail() {
        try {
            System.out.println("TESTING CUSTOMER LOGIN FAIL");
        UserDetails userDetails = new UserDetails("vfvs", "xcvxcv", ClientType.CUSTOMER);
            loginManager.login(userDetails);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(2)
    public void purchaseCouponTest() {
        System.out.println("TESTING CUSTOMER COUPON PURCHASE");
        List<Coupon> couponsFromDB = couponRepo.findAll();
        customerService.purchaseCoupon(couponsFromDB.get(0));
        customerService.purchaseCoupon(couponsFromDB.get(1));
    }

    @Test
    @Order(3)
    public void purchaseCouponFail() {
        try {
            System.out.println("TESTING CUSTOMER COUPON PURCHASE FAIL");
            List<Coupon> couponsFromDB = couponRepo.findAll();
            customerService.purchaseCoupon(couponsFromDB.get(0));
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void getCustomerCouponsTest() {
        System.out.println("TESTING GET CUSTOMER COUPONS");
        TablePrinter.print(customerService.getCustomerCoupons());
    }

    @Test
    @Order(5)
    public void getCustomerCouponsCategoryTest() {
        System.out.println("TESTING GET CUSTOMER COUPONS BY CATEGORY");
        TablePrinter.print(customerService.getCustomerCoupons(Category.HOLIDAY));
    }

    @Test
    @Order(6)
    public void getCustomerCouponMaxPrice() {
        System.out.println("TESTING GET CUSTOMER COUPONS BY MAX PRICE");
        TablePrinter.print(customerService.getCustomerCoupons(100));
    }

    @Test
    @Order(7)
    public void getCustomerDetails() {
        System.out.println("TESTING GET CUSTOMER DETAILS");
        TablePrinter.print(customerService.getCustomerDetails());
    }

}
