package com.careconnect.careconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.careconnect.careconnect.models.FunctionalIoTDevice;

//Karvspesifikasjon 19 - Testkrav


@Configuration
public class TestConfigIot {
    @Bean
    public FunctionalIoTDevice functionalIoTDevice() {
        FunctionalIoTDevice device = new FunctionalIoTDevice();
        return device;
    }
}