package com.careconnect.careconnect.GUI;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Journal {
    private final DoorLock doorLock;
    private JSONArray patients;
    private JTextArea journalTextArea;

    public Journal() {
        this.doorLock = new DoorLock();
    }

    public void performAction() {
        String apiUrl = "http://localhost:8080/api/patientInfo";

        try {
            // Les JSON-data fra API og oppdater `patients`-array
            reloadJsonData(apiUrl);

            StringBuilder journalContent = new StringBuilder();

            for (int i = 0; i < patients.length(); i++) {
                JSONObject patient = patients.getJSONObject(i);
                JSONObject personligdata = patient.getJSONObject("personligdata");
                journalContent.append("Navn: ").append(personligdata.getString("name")).append("\n");
                journalContent.append("Etternavn: ").append(personligdata.getString("surname")).append("\n");
                journalContent.append("Adresse: ").append(personligdata.getString("address")).append("\n");
                journalContent.append("Telefonnummer: ").append(personligdata.getString("telefonnummer")).append("\n");

                journalContent.append("Diagnoser: ");
                JSONArray diagnoser = patient.getJSONObject("opplysninger").getJSONArray("diagnoser");
                journalContent.append(convertJSONArrayToString(diagnoser)).append("\n");

                journalContent.append("Medikamenter:\n");
                JSONArray medisiner = patient.getJSONObject("opplysninger").getJSONArray("medisiner");
                for (int j = 0; j < medisiner.length(); j++) {
                    JSONObject medication = medisiner.getJSONObject(j);
                    journalContent.append("  - ").append(medication.getString("name"))
                            .append(", Dose: ").append(medication.getString("dose"))
                            .append(", Frekvens: ").append(medication.getString("frequency")).append("\n");
                }

                JSONObject vitalia = patient.getJSONObject("opplysninger").getJSONObject("vitalia");
                journalContent.append("Vitalia:\n");
                journalContent.append("  NEWS SCORE: ").append(vitalia.getInt("NEWS SCORE")).append("\n");
                journalContent.append("  BT: ").append(vitalia.getString("BT")).append("\n");
                journalContent.append("  Puls: ").append(vitalia.getInt("Puls")).append("\n");
                journalContent.append("  Respirasjonsfrekvens: ").append(vitalia.getInt("Respirasjonsfrekvens")).append("\n");
                journalContent.append("  Bevissthetsnivå: ").append(vitalia.getString("Bevissthetsnivå")).append("\n");
                journalContent.append("  Blodsukker: ").append(vitalia.getDouble("Blodsukker")).append("\n");
                journalContent.append("  Temp: ").append(vitalia.getDouble("Temp")).append("\n");

                journalContent.append("Legebesøk:\n");
                JSONArray legebesøk = patient.getJSONArray("pasientlogg");
                for (int k = 0; k < legebesøk.length(); k++) {
                    JSONObject visit = legebesøk.getJSONObject(k);
                    journalContent.append("  Dato: ").append(visit.getString("dato")).append("\n");
                    journalContent.append("  Beskrivelse: ").append(visit.getString("spl_rapport")).append("\n");
                }
            }

            // Skape GUI komponenter basert på hentet data
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

            journalTextArea = new JTextArea(journalContent.toString());
            journalTextArea.setEditable(false);
            mainPanel.add(new JScrollPane(journalTextArea));

            JPanel editPanel = new JPanel();
            editPanel.setLayout(new GridLayout(1, 1));
            JButton editJournalButton = new JButton("Change Journal Info");
            editJournalButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (patients.length() > 0) {
                        JSONObject patient = null;
                        try {
                            patient = patients.getJSONObject(0);
                        } catch (JSONException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            editPatientInfo(patient);
                        } catch (JSONException | IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No patients available.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            editPanel.add(editJournalButton);
            mainPanel.add(editPanel);

            // Legg til IOT enheter
            JPanel iotPanel = new JPanel(new GridLayout(1, 3, 10, 10));
            iotPanel.setBorder(BorderFactory.createTitledBorder("IOT Units:"));

            JButton doorLockButton = new JButton("Door Lock");
            doorLockButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doorLock.toggleLock();
                    JOptionPane.showMessageDialog(null, doorLock.getLockStatus(), "Door Lock Status", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            JButton bedTrackerButton = new JButton("Bed Tracker");
            bedTrackerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Bed tracker not implemented yet!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            JButton pillDistributorButton = new JButton("Pill Distributor");
            pillDistributorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Pill distributor not implemented yet!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            iotPanel.add(doorLockButton);
            iotPanel.add(bedTrackerButton);
            iotPanel.add(pillDistributorButton);

            mainPanel.add(iotPanel);

            JOptionPane.showMessageDialog(null, mainPanel, "Patient Journal", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void reloadJsonData(String apiUrl) throws IOException, JSONException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject root = new JSONObject(response.body());
        patients = root.getJSONArray("patientsjournal");
    }

    private void editPatientInfo(JSONObject patient) throws JSONException, IOException, InterruptedException {
        String[] options = {"Name", "Surname", "Address", "Phone Number", "Diagnoses", "Vital Signs"};
        String selectedOption = (String) JOptionPane.showInputDialog(null,
                "Select the field to edit:",
                "Edit Patient Info",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (selectedOption == null) return;

        switch (selectedOption) {
            case "Name":
                String newName = JOptionPane.showInputDialog("Enter new name:", patient.getJSONObject("personligdata").getString("name"));
                if (newName != null && !newName.isEmpty()) {
                    patient.getJSONObject("personligdata").put("name", newName);
                }
                break;
            case "Surname":
                String newSurname = JOptionPane.showInputDialog("Enter new surname:", patient.getJSONObject("personligdata").getString("surname"));
                if (newSurname != null && !newSurname.isEmpty()) {
                    patient.getJSONObject("personligdata").put("surname", newSurname);
                }
                break;
            case "Address":
                String newAddress = JOptionPane.showInputDialog("Enter new address:", patient.getJSONObject("personligdata").getString("address"));
                if (newAddress != null && !newAddress.isEmpty()) {
                    patient.getJSONObject("personligdata").put("address", newAddress);
                }
                break;
            case "Phone Number":
                String newPhoneNumber = JOptionPane.showInputDialog("Enter new phone number:", patient.getJSONObject("personligdata").getString("telefonnummer"));
                if (newPhoneNumber != null && !newPhoneNumber.isEmpty()) {
                    patient.getJSONObject("personligdata").put("telefonnummer", newPhoneNumber);
                }
                break;
            case "Diagnoses":
                JSONArray diagnoses = patient.getJSONObject("opplysninger").getJSONArray("diagnoser");
                String newDiagnoses = JOptionPane.showInputDialog("Enter new diagnoses (comma separated):", convertJSONArrayToString(diagnoses));
                if (newDiagnoses != null && !newDiagnoses.isEmpty()) {
                    JSONArray newDiagnosesArray = new JSONArray(newDiagnoses.split(","));
                    patient.getJSONObject("opplysninger").put("diagnoser", newDiagnosesArray);
                }
                break;
            case "Vital Signs":
                JSONObject vitalSigns = patient.getJSONObject("opplysninger").getJSONObject("vitalia");
                String newBloodPressure = JOptionPane.showInputDialog("Enter new Blood Pressure:", vitalSigns.getString("BT"));
                if (newBloodPressure != null && !newBloodPressure.isEmpty()) {
                    vitalSigns.put("BT", newBloodPressure);
                }
                break;
            default:
                break;
        }

        // Lagre oppdatert JSON-data til API (forutsatt at det finnes en API-endpoint for å håndtere oppdatering)
        saveUpdatedJsonToFile();
        reloadJsonData("http://localhost:8080/api/patientInfo");

        // Oppdater GUI med ny data
        refreshJournalDisplay();
    }

    private void saveUpdatedJsonToFile() throws IOException, JSONException, InterruptedException {
        String apiUrl = "http://localhost:8080/api/updatePatientInfo"; // API-endpoint for oppdatering av JSON-data
        JSONObject root = new JSONObject();
        root.put("patientsjournal", patients);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(root.toString()))
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private void refreshJournalDisplay() {
        StringBuilder journalContent = new StringBuilder();

        try {
            for (int i = 0; i < patients.length(); i++) {
                JSONObject patient = patients.getJSONObject(i);
                JSONObject personligdata = patient.getJSONObject("personligdata");
                journalContent.append("Navn: ").append(personligdata.getString("name")).append("\n");
                journalContent.append("Etternavn: ").append(personligdata.getString("surname")).append("\n");
                journalContent.append("Adresse: ").append(personligdata.getString("address")).append("\n");
                journalContent.append("Telefonnummer: ").append(personligdata.getString("telefonnummer")).append("\n");

                journalContent.append("Diagnoser: ");
                JSONArray diagnoser = patient.getJSONObject("opplysninger").getJSONArray("diagnoser");
                journalContent.append(convertJSONArrayToString(diagnoser)).append("\n");

                journalContent.append("Medikamenter:\n");
                JSONArray medisiner = patient.getJSONObject("opplysninger").getJSONArray("medisiner");
                for (int j = 0; j < medisiner.length(); j++) {
                    JSONObject medication = medisiner.getJSONObject(j);
                    journalContent.append("  - ").append(medication.getString("name"))
                            .append(", Dose: ").append(medication.getString("dose"))
                            .append(", Frekvens: ").append(medication.getString("frequency")).append("\n");
                }

                JSONObject vitalia = patient.getJSONObject("opplysninger").getJSONObject("vitalia");
                journalContent.append("Vitalia:\n");
                journalContent.append("  NEWS SCORE: ").append(vitalia.getInt("NEWS SCORE")).append("\n");
                journalContent.append("  BT: ").append(vitalia.getString("BT")).append("\n");
                journalContent.append("  Puls: ").append(vitalia.getInt("Puls")).append("\n");
                journalContent.append("  Respirasjonsfrekvens: ").append(vitalia.getInt("Respirasjonsfrekvens")).append("\n");
                journalContent.append("  Bevissthetsnivå: ").append(vitalia.getString("Bevissthetsnivå")).append("\n");
                journalContent.append("  Blodsukker: ").append(vitalia.getDouble("Blodsukker")).append("\n");
                journalContent.append("  Temp: ").append(vitalia.getDouble("Temp")).append("\n");

                journalContent.append("Legebesøk:\n");
                JSONArray legebesøk = patient.getJSONArray("pasientlogg");
                for (int k = 0; k < legebesøk.length(); k++) {
                    JSONObject visit = legebesøk.getJSONObject(k);
                    journalContent.append("  Dato: ").append(visit.getString("dato")).append("\n");
                    journalContent.append("  Beskrivelse: ").append(visit.getString("spl_rapport")).append("\n");
                }
            }

            journalTextArea.setText(journalContent.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String convertJSONArrayToString(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return String.join(", ", list);
    }
}