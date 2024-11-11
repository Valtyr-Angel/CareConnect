package com.careconnect.careconnect.models;

public class admin extends User{
    
    private String userRole="ADMIN";
    admin(String firstName, String lastName, Long userId,String username, String role, String password){
        super(firstName, lastName, userId,username, role, password);
    }
}

