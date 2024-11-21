package com.careconnect.careconnect;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration.DslContextConfiguration;

import com.careconnect.careconnect.models.User;
import com.careconnect.careconnect.models.doctor;

public class UserTest{
    @Test
    public void TestCreateUser(){
       User user = new User("Ola", "Nordmann", (long) 2, "OlaRuler123", "passord", "USER");
       
       String expectedFirstName = "Ola";
        String expectedLastName = "Nordmann";
        Long expectedId = 2L;
        String expectedUsername = "OlaRuler123";
        String expectedPassword = "passord";
        String expectedRole = "USER";

        assertEquals(expectedFirstName, user.getFirstName());
        assertEquals(expectedLastName, user.getLastName());
        assertEquals(expectedId, user.getUserId());
        assertEquals(expectedUsername, user.getUsername());
        assertEquals(expectedPassword, user.getPassword());
        assertEquals(expectedRole, user.getRole());
    }

}