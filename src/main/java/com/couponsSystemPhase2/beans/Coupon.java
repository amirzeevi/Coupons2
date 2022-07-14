package com.couponsSystemPhase2.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
/**
 * This is the Coupon bean that represents all coupon that will be used in the system. As well as the entities
 * to be created in the database. 
 */

@Entity
@NoArgsConstructor
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "company_id")
    private int companyID;
    private String title;
    private String description;
    private Category category;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;

    /**
     * Required constructor for using lombok Builder to build a new coupon.
     */
    @Builder
    public Coupon(
            int companyID,
            Category category,
            String title,
            String description,
            Date startDate,
            Date endDate,
            int amount,
            double price,
            String image) {
        this.companyID = companyID;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }
}



