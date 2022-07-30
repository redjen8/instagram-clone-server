package com.redjen.instagram.service;

import com.redjen.instagram.domain.common.CustomException;
import com.redjen.instagram.domain.common.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

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

    public String getAccessTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("access-token");
    }

    public String getRefreshTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("refresh-token");
    }

    public String publishAccessToken(Long userId) {
        return generateNewToken(userId, ACCESS_TOKEN_EXPIRE_TIME);
    }

    public String publishRefreshToken(Long userId) {
        return generateNewToken(userId, REFRESH_TOKEN_EXPIRE_TIME);
    }

    public Boolean isTokenValid(String token) {
        Date expireDate = validateTokenAndReturnBody(token).getExpiration();
        return expireDate.before(new Date());
    }

    private String generateNewToken(Long id, Long tokenExpireTime) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .claim("userId", id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpireTime))
                .signWith(jwtKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims validateTokenAndReturnBody(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(jwtKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e1) {
            log.error("Invalid or Illegal JWT Token used.");
            throw new CustomException(ErrorCode.INVALID_FORMAT_TOKEN);
        } catch (ExpiredJwtException e2) {
            log.error("Expired JWT Token used.");
            throw new CustomException(ErrorCode.EXPIRED_ACCESS_TOKEN);
        }
    }
}
