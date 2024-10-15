package com.careconnect.careconnect;

import java.util.ArrayList;

import org.hibernate.Length;

public class doctor extends user {
    
    private ArrayList<Integer> assignedPatients = new ArrayList<>();

    public void addAssignedPatient(int i){
        this.assignedPatients.add(i);
    }
    public void removeAssignedPatient(int i){
        this.assignedPatients.remove(i);
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
        sb.append("Navn: ").append(this.getFirstName()).append(this.getLastName()).append("\n");
        sb.append("Patients: ").append(this.getAssignedPatients()).append("\n");
        return sb.toString();
    }
}
