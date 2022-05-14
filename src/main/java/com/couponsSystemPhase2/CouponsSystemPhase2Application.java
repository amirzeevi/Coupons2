package com.couponsSystemPhase2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponsSystemPhase2Application {

    public static void main(String[] args) {
        SpringApplication.run(CouponsSystemPhase2Application.class, args);
        System.out.println("Server running");
    }
}
