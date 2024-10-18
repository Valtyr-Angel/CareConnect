package com.careconnect.careconnect.models;

import java.util.ArrayList;

public class doctor extends user {
    
    private ArrayList<Integer> assignedPatients = new ArrayList<>();

    doctor(String firstN, String lastN, int id, String role, String password){
        super(firstN, lastN, id, role, password);

    }
    public void addAssignedPatient(Object patientId) {
        if (patientId instanceof Integer) {
            this.assignedPatients.add((Integer) patientId);
        } else {
            throw new IllegalArgumentException("Invalid type: patientId must be an integer.");
        }
    }
    
    public void removeAssignedPatient(Object patientId) {
        if (patientId instanceof Integer) {
            this.assignedPatients.remove(Integer.valueOf((Integer) patientId));
        } else {
            throw new IllegalArgumentException("Invalid type: patientId must be an integer.");
        }
    }
    public String getAssignedPatients() {
        StringBuilder sb = new StringBuilder();
        for (int id : assignedPatients) {
            sb.append(id).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Navn: ").append(this.getFirstName()).append(" ").append(this.getLastName()).append("\n");
        sb.append("Patients: ").append(this.getAssignedPatients()).append("\n");
        return sb.toString();
    }
}
