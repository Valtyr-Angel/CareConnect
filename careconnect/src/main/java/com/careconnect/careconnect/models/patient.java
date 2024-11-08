package com.careconnect.careconnect.models;

public class Patient extends Person {
    private int patientId;
    private String patientAddress;
    private int alder;
    private String journal;

    public Patient() {
        super("defaultFirstName", "defaultLastName"); // Call to the superclass constructor
        // Default constructor for Jackson
    }

    public Patient(String firstName, String lastName, int id, String address, int alder, String journal) {
        super(firstName, lastName);
        this.patientId = id;
        this.patientAddress = address;
        this.alder = alder;
        this.journal = journal;
    }

    public void setPatientId(int id) {
        this.patientId = id;
    }

    public int getPatientId() {
        return this.patientId;
    }

    public void setAddress(String address) {
        this.patientAddress = address;
    }

    public String getAddress() {
        return this.patientAddress;
    }

    public int getAlder() {
        return alder;
    }

    public void setAlder(int alder) {
        this.alder = alder;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }
}