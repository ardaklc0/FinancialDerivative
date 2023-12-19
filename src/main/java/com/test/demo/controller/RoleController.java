package com.test.demo.controller;
import com.test.demo.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService userService;
    @RequestMapping("/role-home")
    public String role(){
        return "role";
    }
}
