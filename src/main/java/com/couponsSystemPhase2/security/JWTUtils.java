package com.couponsSystemPhase2.security;

import com.couponsSystemPhase2.exception.TokenException;
import com.couponsSystemPhase2.service.ClientType;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Map;

@Component
public class JWTUtils {
    private final String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    private final String encodedSecretKey = "this+is+my+secret+key+i+am+the+only+one+who+knows+about+it";
    private final Key decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey), this.signatureAlgorithm);

    public String generateToken(String email, ClientType clientType) {
        Map<String, Object> claims = Map.of("type", clientType);
        return createToken(claims, email);
    }

    public String generateToken(String token) {
        token = token.replace("Bearer: ", "");
        Claims claims = extractAllClaims(token);
        Map<String, Object> mapClaims = Map.of("type", claims.get("type"));
        return createToken(mapClaims, claims.getSubject());
    }

    private String createToken(Map<String, Object> claims, String email) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(java.util.Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .signWith(decodedSecretKey)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(decodedSecretKey).build().parseClaimsJws(token).getBody();
    }

    public boolean isExpired(String token) {
        try {
            extractAllClaims(token);
        } catch (ExpiredJwtException err) {
            return true;
        }
        return false;
    }

    public boolean validateToken(String token, ClientType clientType) {
        Claims claims = extractAllClaims(token);
        if (!(claims.get("type").equals(clientType.toString()))) {
            throw new TokenException("User Not allowed");
        }
        return true;
    }
}
