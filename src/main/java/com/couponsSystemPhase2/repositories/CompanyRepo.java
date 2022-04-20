package com.couponsSystemPhase2.repositories;

import com.couponsSystemPhase2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedQuery;
import java.util.Optional;
import java.util.OptionalInt;


public interface CompanyRepo extends JpaRepository<Company, Integer> {

    boolean existsByNameOrEmail(String name, String email);

    boolean existsByEmailAndIdNot(String email, int id);

    @Query(value = "SELECT id FROM Company " +
            "WHERE Company.email = :email " +
            "AND Company.password = :password", nativeQuery = true)
    Optional<Integer> getCompanyId(String email, String password);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Company c SET c.email = :email, c.password = :password WHERE c.id = :id")
    void updateCompany(String email, String password, int id);
}
