
/**
 * startpunkt for spring-boot og applikasjonen
 * bruker @scan etc for å hente inn ressurser fra de andre modulene for bruk under kjøring
 *  
 */
package com.careconnect.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import careconnect.models.GuiApp;

@ComponentScan(basePackages = {"com.careconnect.careconnect", "com.careconnect.security","com.careconnect.application"})
@SpringBootApplication(scanBasePackages = "com.careconnect.careconnect")
@EntityScan(basePackages = "com.careconnect.careconnect.models")
@EnableJpaRepositories(basePackages = "com.careconnect.careconnect.repository")
public class Application {
    public static void main(String[] args) {
        // Sørg for at GUI-modus ikke er headless
        System.setProperty("java.awt.headless", "false");

        // Start Spring Boot Backend
        SpringApplication.run(Application.class, args);

        // Start GUI Application etter at backend er startet
        javax.swing.SwingUtilities.invokeLater(() -> {
            new GuiApp(); // Start GUI
        });
    }
}
