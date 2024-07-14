package com.manager.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/dashboard")
    public String getUserDashboard() {
        return "user/dashboard";
    }

    @RequestMapping("/profile")
    public String getUserProfile() {
        return "user/profile";
    }
    
}
