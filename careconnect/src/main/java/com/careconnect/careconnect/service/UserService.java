package com.careconnect.careconnect.service;

import com.careconnect.careconnect.domain.User;

public class UserService {
    public boolean authenticate(User user) {
        // Implement authentication logic
        return "admin".equals(user.getUsername()) && "admin".equals(user.getPassword());
    }
}