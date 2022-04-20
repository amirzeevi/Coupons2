package com.couponsSystemPhase2.clr;

import com.couponsSystemPhase2.repositories.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Order(4)
@RequiredArgsConstructor
public class generalTest implements CommandLineRunner {

    private final CompanyRepo companyRepo;

    @Override
    public void run(String... args) throws Exception {

        companyRepo.deleteById(1);
    }
}
