package com.careconnect.careconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public String adminHome() {
        return "admin/home"; // This returns the admin/home.html template
    }
}
