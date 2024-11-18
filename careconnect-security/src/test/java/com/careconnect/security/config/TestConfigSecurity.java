package com.careconnect.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.careconnect.security.service.implementation")
public class TestConfigSecurity {

// setter opp testklasser for Security modulen
// må gjøres for å ha mer hexagonal arkitektur


// instansierer 3 brukere
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        UserDetails adminUser = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails regularUser = User.withUsername("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();

        UserDetails guestUser = User.withUsername("guest")
                .password(passwordEncoder.encode("password"))
                .roles()
                .build();

        return new InMemoryUserDetailsManager(adminUser, regularUser, guestUser);
    }

/*     @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails adminUser = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        UserDetails regularUser = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        UserDetails guestUser = User.withDefaultPasswordEncoder()
                .username("guest")
                .password("password")
                .roles("")
                .build();

        return new InMemoryUserDetailsManager(adminUser, regularUser, guestUser);
    } */

   

@Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        // You can set PasswordEncoder here as well if needed.
        return new ProviderManager(authProvider);
    }

/*     @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    } */


    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    //instansierer enkel innlogginglogikk/redirektion logikk
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/user").hasRole("USER")
                .requestMatchers("/").permitAll()
                //.and().formLogin();
                .and()
            .formLogin().loginPage("/login"); 



    }



@RestController
    static class MockController {

        @GetMapping("/admin")
        public String adminPage() {
            return "Admin page";
        }

        @GetMapping("/user")
        public String userPage() {
            return "User page";
        }

        @GetMapping("/")
        public String homePage() {
            return "Home page";
        }

        @GetMapping("/application/static/styles.css")
        public String staticResources() {
            return "Static Resource";
        }
    }


}
