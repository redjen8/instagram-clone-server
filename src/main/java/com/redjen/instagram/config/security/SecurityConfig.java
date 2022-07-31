package com.redjen.instagram.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Log4j2
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;

    // TODO : WebSecurityConfigurerAdapter 클래스 deprecation 으로 인한 변경 필요

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable()
                .authorizeRequests()
                .antMatchers(whitelistUriPatterns()).permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private String[] whitelistUriPatterns() {
        return new String[]{
                "/member/login/**",
                "/member/register/**"
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
