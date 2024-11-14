package com.careconnect.careconnect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ActiveProfiles;

import com.careconnect.careconnect.config.TestConfigUserRepo; // Import your test config
import com.careconnect.careconnect.repository.UserRepository;
import com.careconnect.careconnect.models.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = TestConfigUserRepo.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User user = new User("Ola", "Nordmann", 2L, "OlaRuler123", "passord", "USER");
        userRepository.save(user);

        User foundUser = userRepository.findByUsername("OlaRuler123");

        assertNotNull(foundUser);
        assertEquals("Ola", foundUser.getFirstName());
        assertEquals("Nordmann", foundUser.getLastName());
        assertEquals(2L, foundUser.getUserId());
        assertEquals("OlaRuler123", foundUser.getUsername());
        assertEquals("passord", foundUser.getPassword());
        assertEquals("USER", foundUser.getRole());
    }
}