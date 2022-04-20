package com.couponsSystemPhase2.service;

import com.couponsSystemPhase2.beans.Company;
import com.couponsSystemPhase2.beans.Customer;
import com.couponsSystemPhase2.exception.CouponException;
import com.couponsSystemPhase2.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService extends ClientService {

    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin") && password.equals("admin");
    }

    public void addCompany(Company company) {
        if (company.getId() != 0) {
            throw new CouponException("You can not enter id");
        }
        if (companyRepo.existsByNameOrEmail(company.getName(), company.getEmail())) {
            throw new CouponException("Company name or email already exists");
        }
        companyRepo.saveAndFlush(company);
        System.out.println("Company added");
    }

    public void updateCompany(Company company) {
        if (!(companyRepo.existsById(company.getId()))) {
            throw new NotFoundException("Company not found");
        }
        if (companyRepo.existsByEmailAndIdNot(company.getEmail(), company.getId())) {
            throw new CouponException("Can not update company; existing email");
        }
        companyRepo.updateCompany(company.getEmail(), company.getPassword(), company.getId());
        System.out.println("Company updated");
    }

    public void deleteCompany(int companyId) {
        if (!companyRepo.existsById(companyId)) {
            throw new NotFoundException("Company not found");
        }
        companyRepo.deleteById(companyId);
        System.out.println("Company deleted");
    }

    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    public Company getOneCompany(int companyID) {
        return companyRepo.findById(companyID)
                .orElseThrow(() -> new NotFoundException("Company not found"));
    }

    public void addCustomer(Customer customer) {
        if (customer.getId() != 0) {
            throw new CouponException("You can not enter id");
        }
        if (customerRepo.existsByEmail(customer.getEmail())) {
            throw new CouponException("Email already exists");
        }
        customerRepo.saveAndFlush(customer);
        System.out.println("Customer added");
    }

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

    public void deleteCustomer(int customerID) {
        if (!customerRepo.existsById(customerID)) {
            throw new NotFoundException("Customer not found");
        }
        customerRepo.deleteById(customerID);
        System.out.println("Customer deleted");
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer getOneCustomer(int customerID) {
        return customerRepo.findById(customerID).orElseThrow(() -> new NotFoundException("Customer not found"));
    }
}
