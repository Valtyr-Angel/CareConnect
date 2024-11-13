package com.careconnect.security.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

@TestConfiguration
public class TestSecurityConfig {

    @Bean
    public SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
        return new SecurityConfig().securityFilterChain(http);
    }

    @Bean
    public RequestPostProcessor adminUser() {
        return user("admin").roles("ADMIN");
    }

    @Bean
    public RequestPostProcessor regularUser() {
        return user("user").roles("USER");
    }
}