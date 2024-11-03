package com.careconnect.security.adapter;
import org.springframework.stereotype.Component;

import com.careconnect.careconnect.port.LoginPort;



@Component
public class LoginAdapter implements LoginPort {
    @Override
    public boolean authenticate(String username, String password) {
        // Pretend we are checking against a database or another system
        return "user".equals(username) && "pass".equals(password);
    }
}
