package com.careconnect.careconnect.models;

abstract class person {
    private String firstName;
    private String lastName;

    person(String firstN, String lastN){
        firstName = firstN;
        lastName = lastN;
    }
    public void setFirstName(String s){
        this.firstName = s;
    }
    public String getFirstName(){
        return(this.firstName);
    }
    public void setLastName(String s){
        this.lastName = s;
    }
    public String getLastName(){
        return(this.lastName);
    }
}
