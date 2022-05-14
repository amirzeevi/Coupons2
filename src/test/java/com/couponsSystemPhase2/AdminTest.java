package com.couponsSystemPhase2;

import com.couponsSystemPhase2.beans.Company;
import com.couponsSystemPhase2.beans.Customer;
import com.couponsSystemPhase2.service.AdminService;
import com.couponsSystemPhase2.service.ClientType;
import com.couponsSystemPhase2.service.LoginManager;
import com.couponsSystemPhase2.utils.TablePrinter;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class AdminTest {
    @Autowired
    public LoginManager loginManager;
    @Autowired
    public AdminService adminService;

    @Test
    @Order(1)
    public void loginFail() {
        try {
            System.out.println("TESTING LOGIN FAIL");
            loginManager.login("Xadmin@admin", "admin", ClientType.ADMINISTRATOR);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(2)
    public void loginTest() {
        try {
            System.out.println("TESTING LOGIN FAIL");
            loginManager.login("admin@admin", "admin", ClientType.ADMINISTRATOR);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @Order(3)
    public void addCompanyTest() {
        Company company1 = Company.builder()
                .name("Fresh clothing")
                .email("fresh@com")
                .password("1234")
                .build();

        Company company2 = Company.builder()
                .name("MacDonald")
                .email("burger@com")
                .password("1234")
                .build();


        Company company3 = Company.builder()
                .name("Sun Sails")
                .email("sails@com")
                .password("1234")
                .build();
        try {
            System.out.println("TESTING ADD COMPANY");
            adminService.addCompany(company1);
            adminService.addCompany(company2);
            adminService.addCompany(company3);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @Order(4)
    public void companyAddFail() {
        Company company1 = Company.builder()
                .name("Fresh clothing")
                .email("fresh@com")
                .password("1234")
                .build();
        try {
            System.out.println("TESTING ADD COMPANY FAIL");
            adminService.addCompany(company1);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void updateCompanyTest() {
        System.out.println("TESTING UPDATE COMPANY");
        Company companyFromDB = adminService.getOneCompany(1);
        companyFromDB.setEmail("new.email@com");
        adminService.updateCompany(companyFromDB);
    }

    @Test
    @Order(6)
    public void updateCompanyFail() {
        try {
            System.out.println("TESTING UPDATE COMPANY FAIL");
            Company companyFromDB = adminService.getOneCompany(1);
            companyFromDB.setEmail("burger@com");
            adminService.updateCompany(companyFromDB);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void deleteCompanyTest() {
        System.out.println("TESTING DELETE COMPANY");
        adminService.deleteCompany(3);
    }

    @Test
    @Order(8)
    public void deleteCompanyFail() {
        try {
            System.out.println("TESTING DELETE COMPANY FAIL");
            adminService.deleteCompany(3);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(9)
    public void getAllCompaniesTest() {
        System.out.println("TESTING GET ALL COMPANIES");
        TablePrinter.print(adminService.getAllCompanies());
    }

    @Test
    @Order(10)
    public void getOneCompany() {
        System.out.println("TESTING GET ONE COMPANY");
        TablePrinter.print(adminService.getOneCompany(1));
    }

    @Test
    @Order(11)
    public void getOneCompanyFail() {
        try {
            System.out.println("TESTING GET ONE COMPANY FAIL");
            TablePrinter.print(adminService.getOneCompany(3213));
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(12)
    public void addCustomerTest() {
        Customer customer1 = Customer.builder()
                .name("Moshe")
                .email("my.email@com")
                .password("1234")
                .build();

        Customer customer2 = Customer.builder()
                .name("John")
                .email("john@com")
                .password("1234")
                .build();

        Customer customer3 = Customer.builder()
                .name("Fredo")
                .email("fredo@com")
                .password("1234")
                .build();

        System.out.println("TESTING ADD CUSTOMER");
        adminService.addCustomer(customer1);
        adminService.addCustomer(customer2);
        adminService.addCustomer(customer3);
    }

    @Test
    @Order(13)
    public void AddCustomerFail() {
        Customer customer1 = Customer.builder()
                .name("Moshe")
                .email("my.email@com")
                .password("1234")
                .build();
        try {
            System.out.println("TESTING ADD CUSTOMER FAIL");
            adminService.addCustomer(customer1);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(14)
    public void updateCustomerTest() {
        System.out.println("TESTING UPDATE CUSTOMER");
        Customer custFromDB = adminService.getOneCustomer(1);
        custFromDB.setEmail("new@com");
        adminService.updateCustomer(custFromDB);
    }

    @Test
    @Order(15)
    public void updateCustomerFail() {
        Customer custFromDB = adminService.getOneCustomer(1);
        custFromDB.setEmail("john@com");
        try {
            System.out.println("TESTING UPDATE CUSTOMER FAIL");
            adminService.updateCustomer(custFromDB);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(16)
    public void deleteCustomerTest() {
        System.out.println("TESTING DELETE CUSTOMER");
        adminService.deleteCustomer(3);
    }

    @Test
    @Order(17)
    public void deleteCustomerFail() {
        try {
            System.out.println("TESTING DELETE CUSTOMER FAIL");
            adminService.deleteCustomer(3);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(18)
    public void getAllCustomersTest() {
        System.out.println("TESTING GET ALL CUSTOMERS");
        TablePrinter.print(adminService.getAllCustomers());
    }

    @Test
    @Order(19)
    public void getOneCustomerTest() {
        System.out.println("TESTING GET ONE CUSTOMER");
        TablePrinter.print(adminService.getOneCustomer(1));
    }

    @Test
    @Order(20)
    public void getOneCustomerFail() {
        try {
            System.out.println("TESTING GET ONE CUSTOMER FAIL");
            TablePrinter.print(adminService.getOneCustomer(13431));
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
