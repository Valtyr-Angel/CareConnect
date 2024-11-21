package com.careconnect.careconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.careconnect.careconnect.SignalScanner;

//Karvspesifikasjon 19 - Testkrav


@Configuration
public class TestConfigScanner {
    @Bean
    public SignalScanner signalScanner() {
        return new SignalScanner();
    }
}
