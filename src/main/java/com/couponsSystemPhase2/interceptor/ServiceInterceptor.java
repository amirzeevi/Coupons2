package com.couponsSystemPhase2.interceptor;

import com.couponsSystemPhase2.security.JWTUtils;
import com.couponsSystemPhase2.service.ClientType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A class that implements Spring HandlerInterceptor so that a http request sent to a controller will
 * be interceded before handling the request and will check the request credentials.
 */
@Component
@Scope("prototype")
@RequiredArgsConstructor
public class ServiceInterceptor implements HandlerInterceptor {

    private final JWTUtils jwtUtils;
    public ClientType clientType;

    /**
     * An overridden method that will take the request token from the header and validate it with JWtUtils class.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");
        return jwtUtils.validateToken(token, clientType);
    }
}
