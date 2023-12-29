package com.test.demo.controller;
import com.test.demo.dto.request.user.CreateNewUserRequest;
import com.test.demo.dto.response.user.GetUserDisplayResponse;
import com.test.demo.model.User;
import com.test.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public String login(){
        return "main/login";
    }
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        return "main/login-error";
    }
    @RequestMapping("/index")
    public String home(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", authentication.getPrincipal());
        return "main/index";
    }
    @RequestMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new CreateNewUserRequest());
        return "main/register_form";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("user") CreateNewUserRequest user, Model model){
        if (userService.findByUsername(user.getUsername()) != null){
            model.addAttribute("user", userService.findByUsername(user.getUsername()));
            return "main/register_failure";
        } else {
            userService.save(user);
            return "main/register_success";
        }
    }
}
