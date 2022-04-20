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

@Component
@RequiredArgsConstructor
public class ServiceProvider {

    private Map<String, ClientService> servicesMap;
    private final JWTUtils jwtUtils;

    public Map<String, ClientService> getServices() {
        return servicesMap;
    }

    public void addService(String token, ClientService service) {
        servicesMap.put(token, service);
    }

    public String refreshToken(String oldToken) {
        ClientService service = servicesMap.get(oldToken);
        String newToken = jwtUtils.generateToken(oldToken);
        servicesMap.remove(oldToken);
        addService(newToken, service);
        return newToken;
    }

    @Async
    @Scheduled(fixedRate = 1000 * 30)
    public void checkExpired() {
        for (String token : servicesMap.keySet()) {
            if (jwtUtils.isExpired(token)) {
                servicesMap.remove(token);
            }
        }
    }

    @Bean
    public Map<String, ClientService> getServicesMap() {
        return servicesMap = new HashMap<>();
    }
}
