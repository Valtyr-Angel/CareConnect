package com.careconnect.security.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careconnect.careconnect.domain.User;
import com.careconnect.careconnect.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginAdapter {

    @Autowired
    private UserService userService;

    @PostMapping
    public String login(@RequestBody User user) {
        if (userService.authenticate(user)) {
            return "Login successful";
        } else {
            return "Login failed";
        }
    }
}
