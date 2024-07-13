package com.manager.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class PageController {
    
    @RequestMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("title", "Smart Contact Manager");
        model.addAttribute("message", "Welcome to Smart Contact Manager!");
        model.addAttribute("githubRepo", "https://github.com/1prabhakarpal/contact-manager");
        return "home";
    }

    @RequestMapping("/about")
    public String getAboutPage() {
        return "about";
    }

    @RequestMapping("/contact")
    public String getContactPage() {
        return "contact";
    }
}
