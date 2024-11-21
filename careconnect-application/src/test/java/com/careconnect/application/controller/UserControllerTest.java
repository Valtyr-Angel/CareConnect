package com.careconnect.application.controller;

import com.careconnect.careconnect.service.UserService;
import com.careconnect.careconnect.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//Karvspesifikasjon 19 - Testkrav


@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {UserController.class, UserControllerTest.TestConfig.class})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Mock UserService and UserRepository to avoid database interaction
    @MockBean
    private UserService userService;
    
    @MockBean
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "testuser", roles = "USER")  // Simulerer en innlogget bruker
    public void testUserPage() throws Exception {
        // Perform GET request and check for expected status and view name
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(view().name("user"));
    }

    // Empty TestConfig to avoid database and JPA setup
    static class TestConfig {
        // We don't need database-related configuration here
    }
}
