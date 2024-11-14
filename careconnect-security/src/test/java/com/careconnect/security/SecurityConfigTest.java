package com.careconnect.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.careconnect.security.config.TestConfigSecurity;

@SpringJUnitConfig
@ContextConfiguration(classes = { TestConfigSecurity.class })
@WebAppConfiguration
public class SecurityConfigTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void shouldAllowAccessToStaticResources() throws Exception {
        mockMvc.perform(get("/application/static/styles.css"))
                .andExpect(status().isOk());
    }

    @Test
void shouldRedirectToLoginForUnauthenticatedUserTryingToAccessAdminPage() throws Exception {
    mockMvc.perform(get("/admin"))
            .andDo(result -> {
                System.out.println("Response Status: " + result.getResponse().getStatus());
                System.out.println("Redirected URL: " + result.getResponse().getRedirectedUrl());
                System.out.println("Error Message: " + result.getResponse().getErrorMessage());
            })
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("**/login"));
}

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldAllowAccessToAdminPageForAdminUser() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void shouldNotAllowAccessToAdminPageForRegularUser() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void shouldAllowAccessToUserPageForUser() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldRedirectToLoginForUnauthenticatedUserTryingToAccessUserPage() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    void shouldAllowAccessToHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}
