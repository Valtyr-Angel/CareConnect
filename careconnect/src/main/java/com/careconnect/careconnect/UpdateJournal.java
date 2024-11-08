/* 

package com.careconnect.careconnect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class UpdateJournal {
    private static final String FILE_PATH = "patientJournal.json";

    public static void main(String[] args) {
        // Oppdaterer pasientjournal
        updatePatientRecord("12345", "Ny oppdatering om pasientens tilstand");
    }

    // Metode for å oppdatere journalen
    public static void updatePatientRecord(String patientId, String newNote) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (InputStream inputStream = UpdateJournal.class.getClassLoader().getResourceAsStream(FILE_PATH);
             InputStreamReader reader = new InputStreamReader(inputStream)) {

            // Leser eksisterende journaler
            Map<String, Map<String, Object>> patientRecords = gson.fromJson(reader, Map.class);

            // Oppdaterer journalen
            if (patientRecords == null) {
                patientRecords = new HashMap<>();
            }

            Map<String, Object> patient = patientRecords.get(patientId);
            if (patient != null) {
                patient.put("journal", newNote); // Oppdaterer kun journalen
            } else {
                System.out.println("Pasient med ID " + patientId + " finnes ikke.");
                return;
            }

            // Merk: Skriving tilbake til filen fungerer ikke for ressurser under kjøring, siden de er "read-only".
            System.out.println("Journal oppdatert for pasient " + patientId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metode for å hente journalen
    public static Map<String, Object> getPatientRecord(String patientId) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (InputStream inputStream = UpdateJournal.class.getClassLoader().getResourceAsStream(FILE_PATH);
             InputStreamReader reader = new InputStreamReader(inputStream)) {

            Map<String, Map<String, Object>> patientRecords = gson.fromJson(reader, Map.class);

            return patientRecords != null ? patientRecords.get(patientId) : null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


*/