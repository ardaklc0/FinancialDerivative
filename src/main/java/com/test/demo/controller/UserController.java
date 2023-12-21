package com.test.demo.controller;
import com.test.demo.dto.request.user.UpdateExistingUserRequest;
import com.test.demo.model.User;
import com.test.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/user-home")
    public String user(Model model, Authentication authentication){
        model.addAttribute("user", authentication.getPrincipal());
        return "user";
    }
    @GetMapping("/edit-user/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "update_user";
    }
    @PostMapping("/update-user/{id}")
    public String updateUser(@PathVariable("id") long id, UpdateExistingUserRequest user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update_user";
        }
        userService.update(user, id);
        User newUser = userService.findById(id);
        model.addAttribute("user", newUser);
        return "index";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        userService.delete(id);
        return "index";
    }

    @GetMapping("/user-list")
    public String userList(Model model){
        model.addAttribute("users", userService.findAll());
        return "show_all_users";
    }
}
