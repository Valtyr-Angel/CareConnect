package com.careconnect.careconnect;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.careconnect")

public class CareconnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareconnectApplication.class, args);
    }
}
