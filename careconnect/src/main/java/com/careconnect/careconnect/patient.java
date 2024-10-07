package com.careconnect.careconnect;

public class patient extends person {
    
    private int patientId;

    private String adress;

    public void setPatientId(int i){
        this.patientId = i;
    }
    public int getPatientId(){
        return(this.patientId);
    }
    public void setAdress(String a){
        this.adress = a;
    }
    public String getAdress(){
        return(this.adress);
    }
}