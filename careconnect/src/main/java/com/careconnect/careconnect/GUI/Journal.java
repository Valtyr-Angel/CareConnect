package com.careconnect.careconnect.GUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Journal {

    // Method to read the JSON file and display its contents in a dialog
    public void performAction() {
        String jsonFile = "C:\\Users\\eivin\\IdeaProjects\\showcase\\src\\pasientInfo.json"; // Path to your JSON file

        try {
            // Read the file content as a String
            String content = new String(Files.readAllBytes(Paths.get(jsonFile)));

            // Parse the JSON object
            JSONObject jsonObject = new JSONObject(content);
            // Get the patients journal array
            JSONArray patients = jsonObject.getJSONArray("patientsjournal");

            // Build the content to display
            StringBuilder journalContent = new StringBuilder();

            for (int i = 0; i < patients.length(); i++) {
                JSONObject patient = patients.getJSONObject(i);
                JSONObject personalData = patient.getJSONObject("personligdata");
                JSONObject opplysninger = patient.getJSONObject("opplysninger");
                JSONArray medisiner = opplysninger.getJSONArray("medisiner");
                JSONArray pasientlogg = patient.getJSONArray("pasientlogg");

                // Append the patient's information
                journalContent.append("Navn: ").append(personalData.getString("name")).append("\n");
                journalContent.append("Etternavn: ").append(personalData.getString("surname")).append("\n");
                journalContent.append("Adresse: ").append(personalData.getString("address")).append("\n");
                journalContent.append("Telefonnummer: ").append(personalData.getString("telefonnummer")).append("\n");

                // Append medications
                journalContent.append("Medikamenter: ");
                for (int j = 0; j < medisiner.length(); j++) {
                    JSONObject med = medisiner.getJSONObject(j);
                    journalContent.append(med.getString("name")).append(" (").append(med.getString("dose")).append(", ").append(med.getString("frequency")).append(")");
                    if (j < medisiner.length() - 1) {
                        journalContent.append(", ");
                    }
                }
                journalContent.append("\n");

                // Append patient log entries
                journalContent.append("Pasientlogg:\n");
                for (int k = 0; k < pasientlogg.length(); k++) {
                    JSONObject logEntry = pasientlogg.getJSONObject(k);
                    journalContent.append("  Dato: ").append(logEntry.getString("dato")).append(", ");
                    journalContent.append("Rapport: ").append(logEntry.getString("spl_rapport")).append("\n");
                }

                journalContent.append("\n------------------\n");
            }

            // Display the journal content in a message dialog
            JOptionPane.showMessageDialog(null, journalContent.toString(), "Journal Content", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
