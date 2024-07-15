package com.manager.contact.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manager.contact.entities.User;
import com.manager.contact.forms.UserForm;
import com.manager.contact.helper.Message;
import com.manager.contact.helper.MessageType;
import com.manager.contact.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;



@Log4j2
@Controller
public class PageController {

    @Autowired
    private UserService userService;
    
    @RequestMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("title", "Smart Contact Manager");
        model.addAttribute("message", "Welcome to Smart Contact Manager!");
        model.addAttribute("githubRepo", "https://github.com/1prabhakarpal/contact-manager");
        return "home";
    }

    @RequestMapping("/about")
    public String getAboutPage(Model model) {
        return "about";
    }

    @RequestMapping("/contact")
    public String contactPage() {
        return "contact";

    }

    @RequestMapping("/services")
    public String servicesPage() {
        return "services";
    }
    
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logoutPage(){
        return "redirect:/login?logout=true";
    }
    @RequestMapping("/register")
    public String registerPage(Model model){
        UserForm userForm = new UserForm();

        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult bindingResult, HttpSession session) {
        System.out.println("Processing register");
        System.out.println(userForm);
        if (bindingResult.hasErrors()) {
            return "register";
        }

        Date dob = new Date();
        try {
            dob=new SimpleDateFormat("yyyy-MM-dd").parse(userForm.getDob()); 
            
            System.out.println("DateOfBirth: " + dob); 
            
            User user = User.builder()
            .fName(userForm.getFName())
            .lName(userForm.getLName())
            .email(userForm.getEmail())
            .password(userForm.getPassword())
            .about(userForm.getAbout())
            .gender(userForm.getGender())
            .dob(dob)
            .profilePic("https://shorturl.at/RIn0z")
            .phoneNumber(userForm.getPhone())
            .build();

            User savedUser = userService.saveUser(user);
            System.out.println("Saved User: " + savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getStackTrace());
            Message message = Message.builder().content("Something Went Wrong").type(MessageType.red).build();
            session.setAttribute("message", message);
        }
        Message message = Message.builder().content("Registration Success").type(MessageType.green).build();
        session.setAttribute("message", message);
        return "redirect:/register";
    }
    
}
