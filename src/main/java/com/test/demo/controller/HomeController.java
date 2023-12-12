package com.test.demo.controller;

import com.test.demo.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final UserDetailsService userService;
    @GetMapping("/")
    public String home(Model model, Authentication authentication){
        if (authentication != null){
            User user = userService.loadUserByUsername()
        }
    }

    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model){
        return "login-error";
    }
}
