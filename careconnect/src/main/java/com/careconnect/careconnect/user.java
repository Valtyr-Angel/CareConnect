package com.careconnect.careconnect;

public class user extends person {
    
    private int userId;

    private String role="USER";//er dette riktig?
    
    private String password;

    public void setUserId(int i){
        this.userId = i;
    }
    public int getUserId(){
        return(this.userId);
    }
    public void setRole(String r){
        this.role = r;
    }
    public String getRole(){
        return(this.role);
    }
    public void setPassword(String p){
        this.password = p;
    }
    public String getPassword(){
        return(this.password);
    }
}
