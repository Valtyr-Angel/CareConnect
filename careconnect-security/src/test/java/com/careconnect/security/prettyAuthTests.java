package com.careconnect.security;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.careconnect.security.service.implementation.AuthenticationServiceImplementation;

@ExtendWith(MockitoExtension.class)
public class prettyAuthTests {

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationServiceImplementation authenticationService;

    @Test
    public void testAuthenticateSuccess() {
        String username = "admin";
        String password = "admin";

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                username, password, Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );

        Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class)))
                .thenReturn(authentication);

        String result = authenticationService.authenticate(username, password);

        
        System.out.println("testAuthenticateSuccess - Username: " + username + ", Password: " + password + ", Result: " + result);
        assertEquals("Authenticated", result);
    }

    @Test
    public void testAuthenticateFail() {
        String username = "wrongUser";
        String password = "wrongPassword";

        Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        String result = authenticationService.authenticate(username, password);

        
        System.out.println("testAuthenticateFail - Username: " + username + ", Password: " + password + ", Result: " + result);
        assertEquals("Authentication Denied", result);
    }

    @Test
    public void testAuthenticateNullUsername() {
        String username = null;
        String password = "password";

        Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        String result = authenticationService.authenticate(username, password);

        
        System.out.println("testAuthenticateNullUsername - Username: " + username + ", Password: " + password + ", Result: " + result);
        assertEquals("Authentication Denied", result);
    }

    @Test
    public void testAuthenticateNullPassword() {
        String username = "user";
        String password = null;

        Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        String result = authenticationService.authenticate(username, password);

        
        System.out.println("testAuthenticateNullPassword - Username: " + username + ", Password: " + password + ", Result: " + result);
        assertEquals("Authentication Denied", result);
    }

    @Test
    public void testAuthenticateEmptyUsername() {
        String username = "";
        String password = "password";

        Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        String result = authenticationService.authenticate(username, password);

        
        System.out.println("testAuthenticateEmptyUsername - Username: " + username + ", Password: " + password + ", Result: " + result);
        assertEquals("Authentication Denied", result);
    }

    @Test
    public void testAuthenticateEmptyPassword() {
        String username = "user";
        String password = "";

        Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        String result = authenticationService.authenticate(username, password);

        
        System.out.println("testAuthenticateEmptyPassword - Username: " + username + ", Password: " + password + ", Result: " + result);
        assertEquals("Authentication Denied", result);
    }

    @Test
    public void testAuthenticateNullUsernameAndPassword() {
        String username = null;
        String password = null;

        Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        String result = authenticationService.authenticate(username, password);

        
        System.out.println("testAuthenticateNullUsernameAndPassword - Username: " + username + ", Password: " + password + ", Result: " + result);
        assertEquals("Authentication Denied", result);
    }

    @Test
    public void testAuthenticateEmptyUsernameAndPassword() {
        String username = "";
        String password = "";

        Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        String result = authenticationService.authenticate(username, password);

        
        System.out.println("testAuthenticateEmptyUsernameAndPassword - Username: " + username + ", Password: " + password + ", Result: " + result);
        assertEquals("Authentication Denied", result);
    }
}
