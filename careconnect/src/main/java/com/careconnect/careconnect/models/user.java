package com.careconnect.careconnect.models;

public class user extends person {
    
    private int userId;

    private String userRole="USER";//er dette riktig?
    
    private String userPassword;
    user(String firstN, String lastN, int id, String role, String password){
        super(firstN, lastN);
        userId = id;
        userRole = role;
        userPassword = password;
    }
    public void setUserId(int i){
        this.userId = i;
    }
    public int getUserId(){
        return(this.userId);
    }
    public void setRole(String r){
        this.userRole = r;
    }
    public String getRole(){
        return(this.userRole);
    }
    public void setPassword(String p){
        this.userPassword = p;
    }
    public String getPassword(){
        return(this.userPassword);
    }
}
