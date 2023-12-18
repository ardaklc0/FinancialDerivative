package com.test.demo.controller;
import com.test.demo.dto.request.user.CreateNewUserRequest;
import com.test.demo.model.User;
import com.test.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        return "login-error";
    }

    @GetMapping("/home")
    public String home(Authentication authentication, Model model){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/register")
    public String register(CreateNewUserRequest user){
        userService.save(user);
        return "redirect:/login";
    }
}
