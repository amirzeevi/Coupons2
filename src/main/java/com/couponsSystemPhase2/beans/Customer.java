package com.couponsSystemPhase2.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @ManyToMany
    @JoinTable(name = "customers_coupons",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "coupons_id",
                    foreignKey = @ForeignKey(value =
                            ConstraintMode.CONSTRAINT,
                            foreignKeyDefinition =
                                    "FOREIGN KEY (coupons_id) " +
                                            "REFERENCES coupon(id) " +
                                            "ON DELETE CASCADE;")))
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Coupon> coupons;

    @Builder
    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
