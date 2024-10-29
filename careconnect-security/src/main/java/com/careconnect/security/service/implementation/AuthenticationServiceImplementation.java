package com.careconnect.security.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.careconnect.security.service.AuthenticationService;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImplementation(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public String authenticate(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            if (authentication.isAuthenticated()) {
                return "Authenticated";
            }
        } catch (AuthenticationException e) {
            return "Authentication Denied";
        }
        return "Authentication Denied";
    }
}