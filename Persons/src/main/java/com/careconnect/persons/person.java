package com.careconnect.persons;

abstract class Person {
    private String firstName;
    private String lastName;

    Person(String firstN, String lastN){
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
