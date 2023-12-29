package com.test.demo.controller;
import com.test.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @RequestMapping("/admin-home")
    public String admin(Model model, Authentication authentication){
        model.addAttribute("admin", authentication.getPrincipal());
        return "admin/admin";
    }
}
