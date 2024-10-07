package com.careconnect.careconnect;

abstract class person {
    private String firstName;
    private String lastName;

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
