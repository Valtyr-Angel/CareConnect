package com.careconnect.security.config;

import com.careconnect.careconnect.port.LoginPort;
import com.careconnect.careconnect.LoginService.LoginService;
import com.careconnect.security.adapter.LoginAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginModuleConfig {
    @Bean
    public LoginPort loginPort() {
        return new LoginAdapter();
    }

    @Bean
    public LoginService loginService(LoginPort loginPort) {
        return new LoginService(loginPort);
    }
}