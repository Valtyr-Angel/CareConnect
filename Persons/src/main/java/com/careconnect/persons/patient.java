package com.careconnect.careconnect.models;

public class patient extends person {
    
    private int patientId;

    private String patientAdress;

    patient(String firstN, String lastN, int id, String adress){
        super(firstN, lastN);
        patientId = id;
        patientAdress = adress;
    }
    public void setPatientId(int i){
        this.patientId = i;
    }
    public int getPatientId(){
        return(this.patientId);
    }
    public void setAdress(String a){
        this.patientAdress = a;
    }
    public String getAdress(){
        return(this.patientAdress);
    }
}