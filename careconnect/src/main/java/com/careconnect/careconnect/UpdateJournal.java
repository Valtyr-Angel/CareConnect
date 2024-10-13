package com.careconnect.careconnect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class UpdateJournal {
    private static final String FILE_PATH = "patientJournal.json";

    public static void main(String[] args) {
        // Oppdaterer pasientjournal
        updatePatientRecord("12345", "Ny oppdatering om pasientens tilstand");
    }

    public static void updatePatientRecord(String patientId, String newNote) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            // Leser eksisterende journaler
            FileReader reader = new FileReader(FILE_PATH);
            Map<String, String> patientRecords = gson.fromJson(reader, Map.class);
            reader.close();

            // Oppdaterer journalen
            if (patientRecords == null) {
                patientRecords = new HashMap<>();
            }
            patientRecords.put(patientId, newNote);

            // Skriver tilbake til filen
            FileWriter writer = new FileWriter(FILE_PATH);
            gson.toJson(patientRecords, writer);
            writer.close();

            System.out.println("Journal oppdatert for pasient " + patientId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
