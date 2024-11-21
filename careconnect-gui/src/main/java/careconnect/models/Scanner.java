<<<<<<< HEAD:careconnect/src/main/java/com/careconnect/careconnect/GUI/Scanner.java
package com.careconnect.careconnect.GUI;
import java.util.ArrayList;
=======
package careconnect.models;

>>>>>>> main_merge:careconnect-gui/src/main/java/careconnect/models/Scanner.java

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