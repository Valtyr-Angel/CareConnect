package com.careconnect.security.handler;

import java.io.IOException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Log the successful authentication event
        logger.info("User '{}' has logged in successfully.", authentication.getName());

        // Log the authorities granted to the user
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        logger.info("User '{}' has roles: {}", authentication.getName(), roles);

        // Determine target URL based on roles
        String targetUrl;
        if (roles.contains("ROLE_ADMIN")) {
            targetUrl = "/admin";
        } else if (roles.contains("ROLE_USER")) {
            targetUrl = "/user";
        } else {
            targetUrl = "/";
        }

        // Log the target URL
        logger.info("Redirecting user '{}' to '{}'", authentication.getName(), targetUrl);

        // Perform the redirection
        response.sendRedirect(request.getContextPath() + targetUrl);
    }
}
