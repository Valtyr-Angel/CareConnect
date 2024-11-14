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

@WebMvcTest(AdminController.class)  // Test AdminController instead of UserController
@ContextConfiguration(classes = {AdminController.class, AdminControllerTest.TestConfig.class})
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Mock UserService and UserRepository to avoid database interaction
    @MockBean
    private UserService userService;
    
    @MockBean
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "adminuser", roles = "ADMIN")  // Simulate an admin user
    public void testAdminPage() throws Exception {
        // Perform GET request and check for expected status and view name
        mockMvc.perform(get("/admin"))  // Assuming the admin page is at "/admin"
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));  // Assuming the view name is "admin"
    }

    // Empty TestConfig to avoid database and JPA setup
    static class TestConfig {
        // We don't need database-related configuration here
    }
}

