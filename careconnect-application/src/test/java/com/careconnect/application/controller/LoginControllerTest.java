package com.careconnect.application.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(LoginController.class)  // Test only the LoginController
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;  // MockMvc to simulate HTTP requests

    @Test
    public void testLoginPageReturnsStatusOkAndLoginView() throws Exception {
        // Perform a GET request to the "/login" endpoint
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())  // Check if status is OK (200)
                .andExpect(view().name("login"))  // Check if the view name is "login"
                .andExpect(model().attributeExists("message"))  // Ensure the model contains the "message" attribute
                .andExpect(model().attribute("message", "Welcome to the Login Page"));  // Verify the value of "message"
    }
}
