package com.careconnect.careconnect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/user/home")
    public String userHome() {
        return "userHome";
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        return "adminHome";
    }

    @GetMapping("/ambulance/home")
    public String ambulanceHome() {
        return "ambulanceHome";
    }
}
    

