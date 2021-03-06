package com.couponsSystemPhase2.clr;

import com.couponsSystemPhase2.beans.Company;
import com.couponsSystemPhase2.beans.Customer;
import com.couponsSystemPhase2.controller.UserDetails;
import com.couponsSystemPhase2.service.AdminService;
import com.couponsSystemPhase2.service.ClientType;
import com.couponsSystemPhase2.service.LoginManager;
import com.couponsSystemPhase2.utils.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * This is the test class for the AdminService class.
 * Implementing all the class's methods and testing them.
 */
@Component
@Order(1)
@RequiredArgsConstructor
public class AdminTest implements CommandLineRunner {
    private final LoginManager loginManager;
    AdminService adminService;

    @Override
    public void run(String... args) throws Exception {
        try {
            UserDetails userDetails = new UserDetails("Xadmin@admin","admin",  ClientType.ADMINISTRATOR );
            System.out.println("TESTING LOGIN FAIL");
            loginManager.login(userDetails);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            UserDetails userDetails = new UserDetails("admin@admin.com","admin",  ClientType.ADMINISTRATOR );
            System.out.println("TESTING LOGIN");
            adminService = (AdminService) loginManager.login(userDetails);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

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
                .email("sail@com")
                .password("1234")
                .build();

        Company company4 = Company.builder()
                .name("Dummy")
                .email("dummy@com")
                .password("1234")
                .build();

        try {
            System.out.println("TESTING ADD COMPANY");
            adminService.addCompany(company1);
            adminService.addCompany(company2);
            adminService.addCompany(company3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("TESTING ADD COMPANY FAIL");
            adminService.addCompany(company1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("TESTING UPDATE COMPANY");
            Company companyFromDB = adminService.getOneCompany(1);
            companyFromDB.setEmail("clothes@com");
            adminService.updateCompany(companyFromDB);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("TESTING UPDATE COMPANY FAIL");
            Company companyFromDB = adminService.getOneCompany(1);
            companyFromDB.setEmail("burger@com");
            adminService.updateCompany(companyFromDB);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("TESTING DELETE COMPANY");
            adminService.deleteCompany(4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("TESTING DELETE COMPANY FAIL");
            adminService.deleteCompany(4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING GET ALL COMPANIES");
        TablePrinter.print(adminService.getAllCompanies());
        System.out.println();

        System.out.println("TESTING GET ONE COMPANY");
        TablePrinter.print(adminService.getOneCompany(1));
        System.out.println();

        try {
            System.out.println("TESTING GET ONE COMPANY FAIL");
            TablePrinter.print(adminService.getOneCompany(3213));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

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

        try {
            System.out.println("TESTING ADD CUSTOMER");
            adminService.addCustomer(customer1);
            adminService.addCustomer(customer2);
            adminService.addCustomer(customer3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("TESTING ADD CUSTOMER FAIL");
            adminService.addCustomer(customer1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("TESTING UPDATE CUSTOMER");
            Customer custFromDB = adminService.getOneCustomer(1);
            custFromDB.setEmail("new@com");
            adminService.updateCustomer(custFromDB);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("TESTING UPDATE CUSTOMER FAIL");
            Customer custFromDB = adminService.getOneCustomer(1);
            custFromDB.setEmail("john@com");
            adminService.updateCustomer(custFromDB);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("TESTING DELETE CUSTOMER");
            adminService.deleteCustomer(3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("TESTING DELETE CUSTOMER FAIL");
            adminService.deleteCustomer(3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING GET ALL CUSTOMERS");
        TablePrinter.print(adminService.getAllCustomers());
        System.out.println();

        System.out.println("TESTING GET ONE CUSTOMER");
        TablePrinter.print(adminService.getOneCustomer(1));
        System.out.println();

        try {
            System.out.println("TESTING GET ONE CUSTOMER FAIL");
            TablePrinter.print(adminService.getOneCustomer(13431));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

}

