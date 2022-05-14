package com.couponsSystemPhase2.repositories;

import com.couponsSystemPhase2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * This is the interface that extends Jpa Repository for sending CRUD built in
 * operations as well as custom operations to database for the Company bean
 */
public interface CompanyRepo extends JpaRepository<Company, Integer> {

    /**
     * Returns true if company exists based in its name or email. Else returns false.
     */
    boolean existsByNameOrEmail(String name, String email);

    /**
     * Returns true if the specified email already exists when updating a company.
     */
    boolean existsByEmailAndIdNot(String email, int id);

    @Query(value = "SELECT id FROM Company " +
            "WHERE Company.email = :email " +
            "AND Company.password = :password", nativeQuery = true)
    Optional<Integer> getCompanyId(String email, String password);

    /**
     * Updates an existing company.
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE Company c SET c.email = :email, c.password = :password WHERE c.id = :id")
    void updateCompany(String email, String password, int id);
}
