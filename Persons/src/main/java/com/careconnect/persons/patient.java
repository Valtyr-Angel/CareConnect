package com.careconnect.persons;

public class Patient extends Person {
    private int patientId;
    private String patientAddress;

    public Patient(String firstName, String lastName, int id, String address) {
        super(firstName, lastName);
        this.patientId = id;
        this.patientAddress = address;
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
}
