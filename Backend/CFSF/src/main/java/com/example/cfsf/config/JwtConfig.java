package com.example.cfsf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {
    private static String secret;
    private static Long expiration;

    @Value("${cfsf.jwt.secret}")
    public void setSecret(String secret) {
        JwtConfig.secret = secret;
    }

    public static String getSecret() {
        return secret;
    }

    @Value("${cfsf.jwt.expiration}")
    public void setExpiration(Long expiration) {
        JwtConfig.expiration = expiration;
    }

    public static Long getExpiration() {
        return expiration;
    }

}
