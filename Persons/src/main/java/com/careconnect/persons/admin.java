package com.careconnect.persons;

public class admin extends user{
    
    private String userRole="ADMIN";
    admin(String firstN, String lastN, int id, String role, String password){
        super(firstN, lastN, id, role, password);
    }
}

