package com.couponsSystemPhase2.interceptor;

import com.couponsSystemPhase2.security.JWTUtils;
import com.couponsSystemPhase2.service.ClientType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class ServiceInterceptor implements HandlerInterceptor {

    private final JWTUtils jwtUtils;
    public ClientType clientType;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");
        return jwtUtils.validateToken(token, clientType);
    }
}
