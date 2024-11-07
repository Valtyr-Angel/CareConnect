/*

package com.careconnect.careconnect;
import java.util.ArrayList;

import com.careconnect.careconnect.GUI.Scanner;
import com.careconnect.careconnect.models.patient;

public class scannerTest {

    public static void main(String[] args) {
        ArrayList<patient> patients = new ArrayList<>();

        patients.add(new patient("John", "Doe", 1, "1234 Elm St"));
        patients.add(new patient("Jane", "Doe", 2, "5678 Oak St"));
        patients.add(new patient("Jim", "Beam", 3, "9101 Maple St"));

        patient foundPatient = Scanner.findPatientById(patients, 2);
        if (foundPatient != null && foundPatient.getFirstName().equals("Jane")) {
            System.out.println("Test passed: Found patient with ID 2 (Jane Doe)");
        } else {
            System.out.println("Test failed: Could not find patient with ID 2");
        }

        patient notFoundPatient = Scanner.findPatientById(patients, 4);
        if (notFoundPatient == null) {
            System.out.println("Test passed: No patient found with ID 4 (as expected)");
        } else {
            System.out.println("Test failed: Unexpected patient found with ID 4");
        }
    }
}




 */