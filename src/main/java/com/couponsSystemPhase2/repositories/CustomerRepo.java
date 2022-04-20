package com.couponsSystemPhase2.repositories;

import com.couponsSystemPhase2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, int id);

    @Query(value = "SELECT id FROM Customer " +
            "WHERE Customer.email = :email " +
            "AND Customer.password = :password", nativeQuery = true)
    Optional<Integer> getCustomerId(String email, String password);

}
