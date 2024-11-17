package com.careconnect.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.careconnect.security.config.TestConfigSecurity;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfigSecurity.class)
public class SecTest {

    private static final Logger logger = LoggerFactory.getLogger(SecTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        try {
            mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .addFilters(new CharacterEncodingFilter("UTF-8", true)) // Add this if there are encoding issues
                .build();
        } catch (Exception e) {
            logger.error("Exception occurred during MockMvc setup: ", e);
        }
    }

    @Test
    public void testAccessPublicResources() throws Exception {
        mockMvc.perform(get("/application/static/somefile"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/application/resources/somefile"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/application/css/somefile"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/application/templates/somefile"))
            .andExpect(status().isOk());
    }

    @Test
    public void testAdminAccessWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/admin"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAdminAccessWithAuthentication() throws Exception {
        mockMvc.perform(get("/admin"))
            .andExpect(status().isOk());
    }

    @Test
    public void testUserAccessWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/user"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testUserAccessWithAuthentication() throws Exception {
        mockMvc.perform(get("/user"))
            .andExpect(status().isOk());
    }

    @Test
    public void testFormLogin() throws Exception {
        mockMvc.perform(formLogin("/login").user("user").password("user"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testGuestAccess() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk());
    }
}
