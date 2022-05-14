package com.couponsSystemPhase2.utils;

import com.couponsSystemPhase2.security.JWTUtils;
import com.couponsSystemPhase2.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * A class for saving the different Company service and Customer service with their JWT token into a map.
 */
@Component
@RequiredArgsConstructor
public class ServiceProvider {

    private Map<String, ClientService> servicesMap;
    private final JWTUtils jwtUtils;

    /**
     * Returns the service map.
     */
    public Map<String, ClientService> getServices() {
        return servicesMap;
    }

    /**
     * Adds the JWT token and ClientService into the map.
     */
    public void addService(String token, ClientService service) {
        servicesMap.put(token, service);
    }

    /**
     * When sending a http response a new JWT token is generated and replaces the old token in the map.
     */
    public String refreshToken(String oldToken) {
        ClientService service = servicesMap.get(oldToken);
        String newToken = jwtUtils.generateToken(oldToken);
        servicesMap.remove(oldToken);
        addService(newToken, service);
        return newToken;
    }

    /**
     * A scheduled operation to perform a clean-up of expired token from the map every 30 minutes.
     */
    @Async
    @Scheduled(fixedRate = 1000 * 30)
    public void checkExpired() {
        for (String token : servicesMap.keySet()) {
            if (jwtUtils.isExpired(token)) {
                servicesMap.remove(token);
            }
        }
    }

    /**
     * A Bean method to initialize the ServiceMap Hash Map.
     */
    @Bean
    public Map<String, ClientService> getServicesMap() {
        return servicesMap = new HashMap<>();
    }
}
