package com.redjen.instagram.service;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;

@Log4j2
@Service
public class JwtService {

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24; // 1일
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; // 7일
    private final Key jwtKey;

    public JwtService(@Value("${jwt.secret}") String jwtSecret) {
        byte[] byteKey = Decoders.BASE64.decode(jwtSecret);
        this.jwtKey = Keys.hmacShaKeyFor(byteKey);
    }

    public String getAccessTokenFromRequest() {
        return "";
    }

    public String getRefreshTokenFromRequest() {
        return "";
    }

    public String publishAccessToken(Long userId) {
        return "";
    }

    public String publishRefreshToken(Long userId) {
        return "";
    }

}
