package com.careconnect.persons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
<<<<<<< HEAD:Persons/src/test/java/com/careconnect/persons/doctortest.java
=======
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration.DslContextConfiguration;
import com.careconnect.careconnect.models.doctor;

//Karvspesifikasjon 19 - Testkrav


>>>>>>> main_merge:careconnect/src/test/java/com/careconnect/careconnect/doctortest.java

public class doctortest {
    @Test
    public void testAddAssignedPatient() {
        doctor doc = new doctor("John", "Doe", (long) 1, "Doctor", "password", "USER");
        doc.addAssignedPatient(123);
        doc.addAssignedPatient(456);
        
        String expectedPatients = "123, 456";
        assertEquals(expectedPatients, doc.getAssignedPatients());
    }
    @Test
    public void testAddAssignedPatientWithInvalidType() {
        doctor doc = new doctor("John", "Doe", (long) 1, "Doctor", "password", "USER");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            doc.addAssignedPatient("abc");  // Passing a String instead of an Integer
        });
        
        String expectedMessage = "Invalid type: patientId must be an integer.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void testRemoveAssignedPatient() {
        doctor doc = new doctor("John", "Doe", (long) 1, "Doctor", "password", "USER");
        doc.addAssignedPatient(123);
        doc.addAssignedPatient(456);
        doc.removeAssignedPatient(Integer.valueOf(123)); // Removing patient with ID 123
        
        String expectedPatients = "456";
        assertEquals(expectedPatients, doc.getAssignedPatients());
    }
    @Test
    public void testRemoveAssignedPatientWithInvalidType() {
        doctor doc = new doctor("John", "Doe", (long) 1, "Doctor", "password", "USER");
        doc.addAssignedPatient(123);  // Adding a valid patient ID
        
        // Attempt to remove a patient using a String (invalid type)
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            doc.removeAssignedPatient("abc");  // Passing a String instead of an Integer
        });

        String expectedMessage = "Invalid type: patientId must be an integer.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testToString() {
        doctor doc = new doctor("John", "Doe", (long) 1, "Doctor", "password", "USER");
        doc.addAssignedPatient(123);
        
        String expectedOutput = "Navn: John Doe\nPatients: 123\n";
        assertEquals(expectedOutput, doc.toString());
    }
}
