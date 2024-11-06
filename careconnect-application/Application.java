
package com.careconnect.application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.careconnect.careconnect", "com.careconnect.security"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}