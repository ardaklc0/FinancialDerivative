package com.test.demo.controller;
import com.test.demo.dto.request.user.CreateNewUserRequest;
import com.test.demo.model.User;
import com.test.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public String login(WebRequest request, Model model){
        User user = userService.findByName(request.getParameter("name"));
        model.addAttribute("user", user);
        return "login";
    }
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        return "login-error";
    }
    @RequestMapping("/index")
    public String home(Model model){
        return "index";
    }
    @RequestMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new CreateNewUserRequest());
        return "register_form";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("user") CreateNewUserRequest user){
        userService.save(user);
        return "register_success";
    }
    @RequestMapping("/user")
    public String user(Model model, Authentication authentication){
        model.addAttribute("user", authentication.getPrincipal());
        return "user";
    }
    //TODO 2: Fix css in the forms.

    @RequestMapping("/admin")
    public String admin(Model model, Authentication authentication){
        model.addAttribute("admin", authentication.getPrincipal());
        return "admin";
    }
}
