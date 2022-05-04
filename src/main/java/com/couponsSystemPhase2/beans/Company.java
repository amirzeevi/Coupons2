package com.couponsSystemPhase2.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * This is the Company bean that represents all companies that use the system. As well as the entities
 * to be created in the database.
 */
@Entity
@NoArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Coupon> coupons;

    /**
     * Required constructor for using the lombok Builder to build a new company.
     */
    @Builder
    public Company(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
