package com.careconnect.careconnect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.careconnect.careconnect.config.SecurityConfig;
import com.careconnect.careconnect.controller.AdminController;
import com.careconnect.careconnect.controller.LoginController;
import com.careconnect.careconnect.controller.UserController;


@ExtendWith(SpringExtension.class)
@WebMvcTest({SecurityConfig.class, AdminController.class, LoginController.class, UserController.class})
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @BeforeEach
    public void setUp() {
        // No need to manually configure MockMvc, @WebMvcTest does it for us
    }

    @Test
    public void testLoginAsAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username", "admin")
                .param("password", "admin")
                .with(csrf()))  // Include CSRF token
                .andExpect(status().isOk());
                

        verify(customAuthenticationSuccessHandler, times(1)).onAuthenticationSuccess(any(), any(), any());
    }
    // alle tester skal returnere 200 status for nå, server kjøres lokalt, så ingen 3xx status
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAdminAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin").with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testUserAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user").with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    public void testLoginAsUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username", "user")
                .param("password", "user")
                .with(csrf()))  // Include CSRF token
                .andExpect(status().isOk());
                

        verify(customAuthenticationSuccessHandler, times(1)).onAuthenticationSuccess(any(), any(), any());
    }

    @Test
public void testAccessDeniedForUser() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/admin").with(csrf()))
            .andExpect(status().is3xxRedirection())  
            .andExpect(redirectedUrl("http://localhost/login"));  
}

    @Test
    public void testLoginPageAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login").with(csrf()))
                .andExpect(status().isOk());
    }
}
