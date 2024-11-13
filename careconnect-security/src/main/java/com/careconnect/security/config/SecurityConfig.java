package com.careconnect.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.careconnect.security.handler.CustomAuthenticationSuccessHandler;

//hovedklassen som tar for seg innlogging, klassen behandler http request og redirigerer bruker til riktig endepunkt med bruk av controllers



@Configuration
@EnableWebSecurity
public class SecurityConfig {
    

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}


// securityfilterchain klassen tar imot http requests, gir alle brukere tilgang til nettressurser, 
//blokkerer tilgang til beskyttede endepunkt ( her /admin og /user, som krever autentisering for tilgang)
        @SuppressWarnings({ "deprecation", "removal" })
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
     .authorizeRequests(authorizeRequests ->
       authorizeRequests
           //.requestMatchers("/login", "/error", "/resources/**", "/static/**", "/css/**", "/js/**", 
                   //"/application/static/**","/application/resources/**",
                   //"/application/css/**","/application/templates/**").permitAll()
                   
                   .requestMatchers(  "/application/static/**","/application/resources/**",
                   "/application/css/**","/application/templates/**").permitAll()
           .requestMatchers("/admin/**").hasRole("ADMIN")
           .requestMatchers("/user/**").hasRole("USER")
           .anyRequest().authenticated()
   )
   // formlogin, selve funksjonen for å kunne logge inn, har her også customAuthenticationSuccessHandler, beskrevet nærmere i egen klasse
                .formLogin(formLogin ->
                    formLogin
                        .loginPage("/login")
                        .successHandler(customAuthenticationSuccessHandler())
                        .permitAll()
                        
                )
                .logout(logout ->
                    logout.permitAll()
                );
            return http.build();
        }
    // for enkel tilgang har vi hardkodet brukerdetaljer for testing og ikke fokus på mer innloggingsfunksjonalitet
    //her er oppgaven for neste team å implementere sjekking mot DB, salting etc.
        @Bean
        public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

            manager.createUser(User.withUsername("user")
                    .password(passwordEncoder.encode("user"))
                    .roles("USER")
                    .build());
            manager.createUser(User.withUsername("admin")
                    .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build());
        return manager;
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    
    
}
