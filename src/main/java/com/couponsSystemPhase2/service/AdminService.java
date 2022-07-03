package com.couponsSystemPhase2.service;

import com.couponsSystemPhase2.beans.Company;
import com.couponsSystemPhase2.beans.Coupon;
import com.couponsSystemPhase2.beans.Customer;
import com.couponsSystemPhase2.exception.CouponException;
import com.couponsSystemPhase2.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A service class for the Administrator user that holds the business logic layer.
 */
@Service
public class AdminService extends ClientService {
    /**
     * Returns true if user email and password match. else, returns false.
     */
    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    /**
     * Adds a new company to the database.
     */
    public int addCompany(Company company) {
        if (company.getId() != 0) {
            throw new CouponException("You can not enter id");
        }
        if (companyRepo.existsByNameOrEmail(company.getName(), company.getEmail())) {
            throw new CouponException("Company name or email already exists");
        }
        System.out.println("Company added");
       return companyRepo.saveAndFlush(company).getId();
    }

    /**
     * Updates an existing company to the database.
     */
    public void updateCompany(Company company) {
        Company companyFromDB = companyRepo.findById(company.getId())
                .orElseThrow(() -> new CouponException("Company not found"));

        if (!company.getName().equals(companyFromDB.getName())) {
            throw new CouponException("Can not change company name");
        }
        if (companyRepo.existsByEmailAndIdNot(company.getEmail(), company.getId())) {
            throw new CouponException("Can not update company; existing email");
        }
        companyRepo.updateCompany(company.getEmail(), company.getPassword(), company.getId());
        System.out.println("Company updated");
    }

    /**
     * Deletes a company from database. If id is not found, throws exception.
     */
    public void deleteCompany(int companyId) {
        if (!companyRepo.existsById(companyId)) {
            throw new NotFoundException("Company not found");
        }
        companyRepo.deleteById(companyId);
        System.out.println("Company deleted");
    }

    /**
     * Returns a list of all companies.
     */
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    /**
     * Returns the specified company.
     */
    public Company getOneCompany(int companyID) {
        return companyRepo.findById(companyID)
                .orElseThrow(() -> new NotFoundException("Company not found"));
    }

    /**
     * Adds a new customer to database.
     */
    public int addCustomer(Customer customer) {
        if (customer.getId() != 0) {
            throw new CouponException("You can not enter id");
        }
        if (customerRepo.existsByEmail(customer.getEmail())) {
            throw new CouponException("Email already exists");
        }
        System.out.println("Customer added");
        return customerRepo.saveAndFlush(customer).getId();
    }

    /**
     * Update an existing customer to database.
     */
    public void updateCustomer(Customer customer) {
        if (!(customerRepo.existsById(customer.getId()))) {
            throw new NotFoundException("Customer not found");
        }
        if (customerRepo.existsByEmailAndIdNot(customer.getEmail(), customer.getId())) {
            throw new CouponException("Can not update; existing customer email");
        }
        customerRepo.saveAndFlush(customer);
        System.out.println("Customer updated");
    }

    /**
     * Deletes the specified customer from database. If id does not exist, throws exception.
     */
    public void deleteCustomer(int customerID) {
        if (!customerRepo.existsById(customerID)) {
            throw new NotFoundException("Customer not found");
        }
        customerRepo.deleteById(customerID);
        System.out.println("Customer deleted");
    }

    /**
     * Returns a list of all customers.
     */
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    /**
     * Returns the specified customer.
     */
    public Customer getOneCustomer(int customerID) {
        return customerRepo.findById(customerID).orElseThrow(() -> new NotFoundException("Customer not found"));
    }
}
