package com.careconnect.careconnect.GUI;
import java.util.ArrayList;

import com.careconnect.careconnect.models.patient;

public class Scanner {

    public static patient findPatientById(ArrayList<patient> patients, int id) {
        for (patient patient : patients) {
            if (patient.getPatientId() == id) {
                return patient;
            }
        }
        return null;
    }
}
