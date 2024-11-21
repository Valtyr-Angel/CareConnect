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


@WebMvcTest(AdminController.class) // Test AdminController
@ContextConfiguration(classes = {AdminController.class, AdminControllerTest.AdminTestConfig.class})
public class AdminControllerTest {  // Class name matches the file name

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

    // Static inner TestConfig class to avoid database-related configuration
    static class AdminTestConfig {
        // Configuration for this test (if any)
    }
}
