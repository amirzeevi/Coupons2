package com.couponsSystemPhase2.controller;

import com.couponsSystemPhase2.service.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO bean when using the login manager controller.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    private String email;
    private String password;
    private ClientType clientType;
}
