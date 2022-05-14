package com.couponsSystemPhase2.repositories;

import com.couponsSystemPhase2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * This is the interface that extends Jpa Repository for sending CRUD built in
 * operations as well as custom operations to database for the Customer bean
 */
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    /**
     * Returns true if customer email exists in database.
     */
    boolean existsByEmail(String email);

    /**
     * Returns true email exists for a different customer when updating a customer.
     */
    boolean existsByEmailAndIdNot(String email, int id);

    /**
     * Returns an Optional for the customer id when trying to match email and password.
     */
    @Query(value = "SELECT id FROM Customer " +
            "WHERE Customer.email = :email " +
            "AND Customer.password = :password", nativeQuery = true)
    Optional<Integer> getCustomerId(String email, String password);

}
