package com.careconnect.careconnect.models;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User extends person implements UserDetails {

    @Id
    private Long userId;
    
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String role;


    public User(String firstName, String lastName, Long userId, String username, String password, String role) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
        this.role = role;
        this.userId = userId;
    }

    // Getters and Setters

    @Override
    public String getUsername() {
        return username;
    }
    public Long getUserId(){
        return userId;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    // nedforliggende atributter er lenket sammen med spring boots egen User/ Userdetails, 
    // er attributter nyttige i databasedrift med aktivering og lockout av brukere
    // ikke noe vi vil bruker per dags dato

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
