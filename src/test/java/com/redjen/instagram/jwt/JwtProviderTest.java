package com.redjen.instagram.jwt;

import com.redjen.instagram.config.security.JwtProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@SpringBootTest
public class JwtProviderTest {

    @Autowired
    JwtProvider jwtProvider;

    @Test
    public void createAccessTokenTest() {
        Long userId = 1L;
        String accessToken = jwtProvider.publishAccessToken(userId);
        assertThat(accessToken).matches("(^[\\w-]*\\.[\\w-]*\\.[\\w-]*$)");
    }

    @Test
    public void createRefreshTokenTest() {
        Long userId = 1L;
        String accessToken = jwtProvider.publishRefreshToken(userId);
        assertThat(accessToken).matches("(^[\\w-]*\\.[\\w-]*\\.[\\w-]*$)");
    }

    @Test
    public void tokenExpireCheckTest() {
        Long userId = 1L;
        String accessToken = jwtProvider.publishAccessToken(userId);
        assertThat(jwtProvider.isTokenValid(accessToken)).isFalse();
    }
}
