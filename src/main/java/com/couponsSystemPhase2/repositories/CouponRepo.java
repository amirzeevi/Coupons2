package com.couponsSystemPhase2.repositories;

import com.couponsSystemPhase2.beans.Category;
import com.couponsSystemPhase2.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Coupon c WHERE c.endDate < CURRENT_DATE")
    void deleteExpiredCoupons();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO customers_coupons (customer_id, coupons_id)" +
                    "VALUES (:customerID, :couponID)", nativeQuery = true)
    void addCouponPurchase(int couponID, int customerID);

    @Query(value = "SELECT * FROM Coupon " +
            "WHERE price <= :maxPrice AND id IN " +
            "(SELECT coupons_id FROM customers_coupons " +
            "WHERE customer_id = :customerID)", nativeQuery = true)
    List<Coupon> getCustomerCouponsByMaxPrice(int customerID, double maxPrice);

    @Query(value = "SELECT * FROM Coupon " +
            "WHERE category = :categoryOrdinal AND id IN " +
            "(SELECT coupons_id FROM customers_coupons " +
            "WHERE customer_id = :customerID)", nativeQuery = true)
    List<Coupon> getCustomerCouponsByCategory(int customerID, int categoryOrdinal);

    @Query(value = "SELECT * FROM Coupon " +
                    "WHERE id IN " +
                    "(SELECT coupons_id FROM customers_coupons " +
                    "WHERE customer_id = :customerID)", nativeQuery = true)
    List<Coupon> getCustomerCoupons(int customerID);

    @Query(value = "SELECT count(*) FROM customers_coupons" +
                    "WHERE coupons_id = :couponID " +
                    "AND customer_id = :customerID", nativeQuery = true)
    int isPurchaseExist(int couponID, int customerID);

    boolean existsByTitleAndCompanyID(String title, int companyID);

    boolean existsByIdAndCompanyID(int id, int companyID);

    boolean existsByTitleAndCompanyIDAndIdNot(String title, int companyID, int id);

    List<Coupon> findAllByCompanyID(int companyID);

    List<Coupon> findAllByCompanyIDAndCategory(int companyID, Category category);

    List<Coupon> findAllByCompanyIDAndPriceLessThanEqual(int CompanyID, double maxPrice);
}
