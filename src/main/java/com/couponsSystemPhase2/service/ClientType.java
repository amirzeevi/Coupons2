package com.couponsSystemPhase2.service;

/**
 * An enum to describe different types of users.
 */
public enum ClientType {

    ADMINISTRATOR(AdminService.class),
    COMPANY(CompanyService.class),
    CUSTOMER(CustomerService.class);

    public final Class<? extends ClientService> serviceClass;

    ClientType(Class<? extends ClientService> serviceClass) {
        this.serviceClass = serviceClass;
    }
}
