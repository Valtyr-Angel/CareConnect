package com.careconnect.careconnect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/ambulance/**").hasRole("AMBULANCE")
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .successHandler((request, response, authentication) -> {
                String role = authentication.getAuthorities().iterator().next().getAuthority();
                if (role.equals("ROLE_ADMIN")) {
                    response.sendRedirect("/admin/home");
                } else if (role.equals("ROLE_USER")) {
                    response.sendRedirect("/user/home");
                } else if (role.equals("ROLE_AMBULANCE")) {
                    response.sendRedirect("/ambulance/home");
                }
            })
            .permitAll()
        )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();

        var admin = User.withUsername("admin")
            .password("{noop}adminpass")
            .roles("ADMIN")
            .build();

        var user = User.withUsername("user")
            .password("{noop}userpass")
            .roles("USER")
            .build();

        var ambulance = User.withUsername("ambulance")
            .password("{noop}userpass")
            .roles("AMBULANCE")
            .build();

        userDetailsManager.createUser(admin);
        userDetailsManager.createUser(user);
        userDetailsManager.createUser(ambulance);

        return userDetailsManager;
    }
}
