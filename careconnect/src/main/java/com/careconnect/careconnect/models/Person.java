package com.careconnect.careconnect.models;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
    private String firstName;
    private String lastName;

    public Person(String firstN, String lastN) {
        this.firstName = firstN;
        this.lastName = lastN;
    }

    public Person() {
    }

    // Standard getters and setters...
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
