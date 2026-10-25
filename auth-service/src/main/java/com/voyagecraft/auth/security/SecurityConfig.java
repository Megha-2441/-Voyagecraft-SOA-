package com.voyagecraft.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            // Disable CSRF for REST API
            .csrf(csrf -> csrf.disable())

            // Configure URL access
            .authorizeHttpRequests(auth -> auth

                // These endpoints are public
                .requestMatchers(
                    "/auth/register",
                    "/auth/login",
                    "/auth/logout",
                    "/auth/users"
                ).permitAll()

                // Other endpoints require authentication
                .anyRequest().authenticated()
            );

        return http.build();
    }
}