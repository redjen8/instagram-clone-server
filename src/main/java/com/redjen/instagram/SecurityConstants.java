package com.redjen.instagram;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {

    @Value("${jwt.secret}")
    public static final String JWT_SECRET_KEY = "";
    public static final String TOKEN_HEADER_PREFIX = "Bearer ";
}
