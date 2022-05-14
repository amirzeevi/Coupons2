package com.couponsSystemPhase2.repositories;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This is the interface that extends Jpa Repository for sending CRUD built in
 * operations as well as custom operations to database for the Coupon bean
 */
public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    /**
     * Delete all coupons that are expired from database.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Coupon c WHERE c.endDate < CURRENT_DATE")
    void deleteExpiredCoupons();

    /**
     * Adds to the customer vs coupons table the id for the customer and coupon.
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO customers_coupons (customer_id, coupons_id)" +
            "VALUES (:customerID, :couponID)", nativeQuery = true)
    void addCouponPurchase(int couponID, int customerID);

    /**
     * Returns a list of specified customer coupons that are of the specified max price
     */
    @Query(value = "SELECT * FROM Coupon " +
            "WHERE price <= :maxPrice AND id IN " +
            "(SELECT coupons_id FROM customers_coupons " +
            "WHERE customer_id = :customerID)", nativeQuery = true)
    List<Coupon> getCustomerCouponsByMaxPrice(int customerID, double maxPrice);

    /**
     * Returns a list of specified customer coupons that are of the specified category
     */
    @Query(value = "SELECT * FROM Coupon " +
            "WHERE category = :categoryOrdinal AND id IN " +
            "(SELECT coupons_id FROM customers_coupons " +
            "WHERE customer_id = :customerID)", nativeQuery = true)
    List<Coupon> getCustomerCouponsByCategory(int customerID, int categoryOrdinal);

    /**
     * Returns a list of all specified customer coupons.
     */
    @Query(value = "SELECT * FROM Coupon " +
            "WHERE id IN " +
            "(SELECT coupons_id FROM customers_coupons " +
            "WHERE customer_id = :customerID)", nativeQuery = true)
    List<Coupon> getCustomerCoupons(int customerID);

    /**
     * Returns the int 1 if the customer already owns the specified coupon. Else returns 0.
     */
    @Query(value = "SELECT count(*) FROM customers_coupons " +
            "WHERE coupons_id = :couponID " +
            "AND customer_id = :customerID", nativeQuery = true)
    int isPurchaseExist(int couponID, int customerID);

    /**
     * Returns true if coupon title already exists for the specified company.
     */
    boolean existsByTitleAndCompanyID(String title, int companyID);

    /**
     * Returns true if the coupon exists for the specified company.
     */
    boolean existsByIdAndCompanyID(int id, int companyID);

    /**
     * Returns true if coupon title exists for a different coupon for the specified company when updating a coupon.
     */
    boolean existsByTitleAndCompanyIDAndIdNot(String title, int companyID, int id);

    /**
     * Returns a list of all the company coupons.
     */
    List<Coupon> findAllByCompanyID(int companyID);

    /**
     * Returns a list of all the company coupons that are of the specified category.
     */
    List<Coupon> findAllByCompanyIDAndCategory(int companyID, Category category);

    /**
     * Returns a list of all the company coupons that are of the specified max price.
     */
    List<Coupon> findAllByCompanyIDAndPriceLessThanEqual(int CompanyID, double maxPrice);
}
