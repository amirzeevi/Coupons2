package com.couponsSystemPhase2;

import com.couponsSystemPhase2.repositories.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class CouponsSystemPhase2Application {

    private final CompanyRepo companyRepo;

    public static void main(String[] args) {
        SpringApplication.run(CouponsSystemPhase2Application.class, args);
        System.out.println("Server running");
    }

    @PreDestroy
    public void drop() {
        companyRepo.dropSchema();
    }
}
