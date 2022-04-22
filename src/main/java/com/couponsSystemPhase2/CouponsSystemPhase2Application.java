package com.couponsSystemPhase2;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

@SpringBootApplication
@EnableScheduling
public class CouponsSystemPhase2Application {
    @Autowired
    EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(CouponsSystemPhase2Application.class, args);
        System.out.println("Server running");
    }

    @PreDestroy
    public void dropSchema() {
        entityManager.createNativeQuery("DROP SCHEMA coupons_system").executeUpdate();
    }
}
