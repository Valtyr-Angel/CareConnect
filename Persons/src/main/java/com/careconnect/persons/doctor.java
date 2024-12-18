package com.careconnect.persons;

import java.util.ArrayList;

public class doctor extends User {
    
    private ArrayList<Integer> assignedPatients = new ArrayList<>();

    public doctor(String firstName, String lastName, Long userId, String username, String password, String role){
        super(firstName, lastName, userId,username, password, role);

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
