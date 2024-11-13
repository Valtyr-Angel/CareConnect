package com.careconnect.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.careconnect.careconnect", "com.careconnect.security","com.careconnect.application"})
@SpringBootApplication(scanBasePackages = "com.careconnect.careconnect")
@EntityScan(basePackages = "com.careconnect.careconnect.models")
@EnableJpaRepositories(basePackages = "com.careconnect.careconnect.repository")

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
