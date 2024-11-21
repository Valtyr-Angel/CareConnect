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

// denne klassen tar for seg logikken etter at brukeren har blitt autentisert av authenticationService 
//klassen sjekker brukerens rettigheter (rolle) og sender bruker til rette endepunkt
// denne funksjonaliteten er viktig for RBAC (role based access controll)
// har også lagt til logger funksjonalitet som viser rettigheter brukeren har, og endepunkt den blir sendt til
// dette blir skrevet til terminal under kjøring

//Kravspesifikasjon 1 - Autentisering/tilgangskontroll



@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    //logger til terminal
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        logger.info("User '{}' has logged in successfully.", authentication.getName());

        //logger brukerrolle til terminal etter autentisering er utført
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        logger.info("User '{}' has roles: {}", authentication.getName(), roles);

        
        String targetUrl;
        if (roles.contains("ROLE_ADMIN")) {
            targetUrl = "/admin";
        } else if (roles.contains("ROLE_USER")) {
            targetUrl = "/user";
        } else {
            targetUrl = "/";
        }

       
        logger.info("Redirecting user '{}' to '{}'", authentication.getName(), targetUrl);

        // bygger URL ut ifra String ovenfor 
        response.sendRedirect(request.getContextPath() + targetUrl);
    }
}
