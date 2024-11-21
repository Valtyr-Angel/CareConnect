package com.careconnect.application.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.careconnect.security.config.SecurityConfig;

@WebMvcTest(LoginController.class)
@ContextConfiguration(classes = {LoginController.class, SecurityConfig.class}) 
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc for simulating HTTP requests

    @Test
    public void testLoginPage() throws Exception {
        // Perform GET request on "/login" and check status and view name
        mockMvc.perform(get("/login"))
               .andExpect(status().isOk()) // Expect HTTP 200 OK
               .andExpect(view().name("login")); // Expect the "login" view
    }
}
