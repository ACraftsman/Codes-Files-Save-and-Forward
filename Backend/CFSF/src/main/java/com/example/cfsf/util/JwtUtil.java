package com.example.cfsf.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.cfsf.config.JwtConfig;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JwtUtil {
    private static final String secret = JwtConfig.getSecret();
    private static final Long expiration = JwtConfig.getExpiration();
    public static String createToken(HashMap<String, Object> claims) {
        JWTCreator.Builder jwtCreator = JWT.create();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            jwtCreator.withClaim(entry.getKey(), Optional.ofNullable(entry.getValue()).map(Object::toString).orElse(null));
        }
        String token = jwtCreator.withExpiresAt(new Date(System.currentTimeMillis() + expiration)).sign(Algorithm.HMAC256(secret));
        return token;
    }

    public static Boolean verifyToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Map<String, Claim> parseToken(String token,@Value("${cfsf.jwt.secret}") String secret) {
        try {
            DecodedJWT decodeToken = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            return decodeToken.getClaims();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
