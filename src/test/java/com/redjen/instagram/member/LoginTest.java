package com.redjen.instagram.member;

import com.redjen.instagram.config.security.JwtProvider;
import com.redjen.instagram.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginTest {

    @Autowired
    MemberService memberService;

    @Autowired
    JwtProvider jwtProvider;

    @DisplayName("사용자 첫 로그인")
    @Test
    public void memberFirstLoginTest() {

    }

    @DisplayName("access token 만료, refresh token 유효")
    @Test
    public void loginWithAccessExpiredAndRefreshValid() {

    }

    @DisplayName("access token 만료, refresh token 만료")
    @Test
    public void loginWithAccessExpiredAndRefreshExpired() {

    }
}
