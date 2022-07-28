package com.redjen.instagram.jwt;

import com.redjen.instagram.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@SpringBootTest
public class JwtServiceTest {

    @Autowired
    JwtService jwtService;

    @Test
    public void createAccessTokenTest() {
        Long userId = 1L;
        String accessToken = jwtService.publishAccessToken(userId);
        assertThat(accessToken).matches("(^[\\w-]*\\.[\\w-]*\\.[\\w-]*$)");
    }

    @Test
    public void createRefreshTokenTest() {
        Long userId = 1L;
        String accessToken = jwtService.publishRefreshToken(userId);
        assertThat(accessToken).matches("(^[\\w-]*\\.[\\w-]*\\.[\\w-]*$)");
    }
}
