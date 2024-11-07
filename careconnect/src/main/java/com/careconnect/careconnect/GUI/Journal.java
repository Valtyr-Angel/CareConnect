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
        String jsonFile = "C:\\Users\\Eivind\\Desktop\\bigdata\\CareConnectTest\\src\\pasientInfo.json"; // Path to your JSON file

        try {
            // Read the file content as a String
            String content = new String(Files.readAllBytes(Paths.get(jsonFile)));

            // Parse the JSON array
            JSONArray patients = new JSONArray(content);

            // Build the content to display
            StringBuilder journalContent = new StringBuilder();

            for (int i = 0; i < patients.length(); i++) {
                JSONObject patient = patients.getJSONObject(i);

                // Append the patient's information
                journalContent.append("Navn: ").append(patient.getString("navn")).append("\n");
                journalContent.append("Etternavn: ").append(patient.getString("etternavn")).append("\n");
                journalContent.append("Alder: ").append(patient.getInt("alder")).append("\n");

                // Append medications
                JSONArray medikamenter = patient.getJSONArray("medikamenter");
                journalContent.append("Medikamenter: ");
                for (int j = 0; j < medikamenter.length(); j++) {
                    journalContent.append(medikamenter.getString(j));
                    if (j < medikamenter.length() - 1) {
                        journalContent.append(", ");
                    }
                }
                journalContent.append("\n");

                // Append doctor visits
                journalContent.append("Legebesøk:\n");
                JSONArray legebesøk = patient.getJSONArray("legebesøk");
                for (int k = 0; k < legebesøk.length(); k++) {
                    JSONObject visit = legebesøk.getJSONObject(k);
                    journalContent.append("  Dato: ").append(visit.getString("dato")).append(", ");
                    journalContent.append("Beskrivelse: ").append(visit.getString("beskrivelse")).append("\n");
                }

                journalContent.append("\n------------------\n");
            }

            // Display the journal content in a message dialog
            JOptionPane.showMessageDialog(null, journalContent.toString(), "Journal Content", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
