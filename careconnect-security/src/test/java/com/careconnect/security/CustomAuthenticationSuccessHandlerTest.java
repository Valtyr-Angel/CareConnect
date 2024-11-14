package com.careconnect.security;

import java.io.IOException;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.test.context.ContextConfiguration;

import com.careconnect.security.config.TestConfigSecurity;
import com.careconnect.security.handler.CustomAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootTest
@ContextConfiguration(classes = TestConfigSecurity.class)
public class CustomAuthenticationSuccessHandlerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAdminRoleRedirection() throws IOException, ServletException {
        UserDetails adminUser = inMemoryUserDetailsManager.loadUserByUsername("admin");
        Authentication authentication = createAuthentication(adminUser);
        
        when(request.getContextPath()).thenReturn("");
        
        successHandler.onAuthenticationSuccess(request, response, authentication);

        verify(response).sendRedirect("/admin");
    }

    @Test
    public void testUserRoleRedirection() throws IOException, ServletException {
        UserDetails regularUser = inMemoryUserDetailsManager.loadUserByUsername("user");
        Authentication authentication = createAuthentication(regularUser);

        when(request.getContextPath()).thenReturn("");

        successHandler.onAuthenticationSuccess(request, response, authentication);

        verify(response).sendRedirect("/user");
    }

    @Test
    public void testNoRoleRedirection() throws IOException, ServletException {
        UserDetails guestUser = inMemoryUserDetailsManager.loadUserByUsername("guest");
        Authentication authentication = createAuthentication(guestUser);
        
        when(request.getContextPath()).thenReturn("");

        successHandler.onAuthenticationSuccess(request, response, authentication);

        verify(response).sendRedirect("/");
    }

    private Authentication createAuthentication(UserDetails userDetails) {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return userDetails.getAuthorities();
            }

            @Override
            public Object getCredentials() {
                return userDetails.getPassword();
            }

            @Override
            public Object getDetails() {
                return userDetails;
            }

            @Override
            public Object getPrincipal() {
                return userDetails;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            }

            @Override
            public String getName() {
                return userDetails.getUsername();
            }
        };
    }
}
