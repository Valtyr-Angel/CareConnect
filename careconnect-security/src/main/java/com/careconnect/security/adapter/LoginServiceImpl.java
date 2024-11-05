
package com.careconnect.security.adapter;

import com.careconnect.careconnect.port.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    
    @Override
    public String login(String username, String password) {
        // Add your login logic here (this is just a simple example)
        if ("admin".equals(username) && "password".equals(password)) {
            return "Login successful!";
        } else {
            return "Invalid credentials!";
        }
    }
}