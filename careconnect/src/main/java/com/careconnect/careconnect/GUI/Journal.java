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
        String jsonFile = "C:\\Users\\Eivind\\Documents\\GitHub\\CareConnect\\careconnect\\src\\main\\java\\com\\careconnect\\careconnect\\GUI\\pasientInfo.json"; // Path to your JSON file

        try {
            // Read the file content as a String
            String content = new String(Files.readAllBytes(Paths.get(jsonFile)));

            // Parse the JSON root object and get the patients array
            JSONObject root = new JSONObject(content);
            JSONArray patients = root.getJSONArray("patientsjournal");

            // Build the content to display
            StringBuilder journalContent = new StringBuilder();

            for (int i = 0; i < patients.length(); i++) {
                JSONObject patient = patients.getJSONObject(i);

                // Append patient personal information
                JSONObject personligdata = patient.getJSONObject("personligdata");
                journalContent.append("Navn: ").append(personligdata.getString("name")).append("\n");
                journalContent.append("Etternavn: ").append(personligdata.getString("surname")).append("\n");
                journalContent.append("Adresse: ").append(personligdata.getString("address")).append("\n");
                journalContent.append("Telefonnummer: ").append(personligdata.getString("telefonnummer")).append("\n");

                // Append diagnoses
                journalContent.append("Diagnoser: ");
                JSONArray diagnoser = patient.getJSONObject("opplysninger").getJSONArray("diagnoser");
                for (int j = 0; j < diagnoser.length(); j++) {
                    journalContent.append(diagnoser.getString(j));
                    if (j < diagnoser.length() - 1) {
                        journalContent.append(", ");
                    }
                }
                journalContent.append("\n");

                // Append medications
                journalContent.append("Medikamenter:\n");
                JSONArray medisiner = patient.getJSONObject("opplysninger").getJSONArray("medisiner");
                for (int j = 0; j < medisiner.length(); j++) {
                    JSONObject medication = medisiner.getJSONObject(j);
                    journalContent.append("  - ").append(medication.getString("name"))
                            .append(", Dose: ").append(medication.getString("dose"))
                            .append(", Frekvens: ").append(medication.getString("frequency")).append("\n");
                }

                // Append vital signs
                journalContent.append("Vitalia:\n");
                JSONObject vitalia = patient.getJSONObject("opplysninger").getJSONObject("vitalia");
                journalContent.append("  NEWS SCORE: ").append(vitalia.getInt("NEWS SCORE")).append("\n");
                journalContent.append("  BT: ").append(vitalia.getString("BT")).append("\n");
                journalContent.append("  Puls: ").append(vitalia.getInt("Puls")).append("\n");
                journalContent.append("  Respirasjonsfrekvens: ").append(vitalia.getInt("Respirasjonsfrekvens")).append("\n");
                journalContent.append("  Bevissthetsnivå: ").append(vitalia.getString("Bevissthetsnivå")).append("\n");
                journalContent.append("  Blodsukker: ").append(vitalia.getDouble("Blodsukker")).append("\n");
                journalContent.append("  Temp: ").append(vitalia.getDouble("Temp")).append("\n");

                // Append doctor visits (log entries)
                journalContent.append("Legebesøk:\n");
                JSONArray legebesøk = patient.getJSONArray("pasientlogg");
                for (int k = 0; k < legebesøk.length(); k++) {
                    JSONObject visit = legebesøk.getJSONObject(k);
                    journalContent.append("  Dato: ").append(visit.getString("dato")).append("\n");
                    journalContent.append("  Beskrivelse: ").append(visit.getString("spl_rapport")).append("\n");
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
