package com.careconnect.careconnect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.careconnect.careconnect.config.TestConfigUserRepo;
import com.careconnect.careconnect.models.User;
import com.careconnect.careconnect.repository.UserRepository;

@ContextConfiguration(classes = TestConfigUserRepo.class)
@SpringBootTest
@ActiveProfiles("test")
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

