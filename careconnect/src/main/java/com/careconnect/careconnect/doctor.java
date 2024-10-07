package com.careconnect.careconnect;

import java.util.ArrayList;

import org.hibernate.Length;

public class doctor extends user {
    
    private ArrayList<Integer> assignedPatients = new ArrayList<>();

    public void addAssignedPatient(int i){
        this.assignedPatients.add(i);
    }
    public String getAssignedPatients() {
        
        for (int id : assignedPatients) {
            System.out.println(id);                    
        }
        return;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Navn: ").append(this.getFirstName()).append(this.getLastName()).append("\n");
        sb.append("Patients: ").append(this.getAssignedPatients()).append("\n");
        return sb.toString();
    }
}
