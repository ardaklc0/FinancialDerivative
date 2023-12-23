package com.test.demo.controller;
import com.test.demo.dto.request.option.CreateNewOptionRequest;
import com.test.demo.dto.request.option.UpdateExistingOptionRequest;
import com.test.demo.model.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.test.demo.services.OptionService;
@Controller
@RequestMapping("/option")
public class OptionController {
    @Autowired
    private OptionService optionService;
    @GetMapping("/option-home")
    public String option(Model model, Authentication authentication){
        model.addAttribute("user", authentication.getPrincipal());
        return "option";
    }
    @GetMapping("/create-option")
    public String showCreateOptionForm(Model model){
        model.addAttribute("option", new CreateNewOptionRequest());
        return "create_option";
    }
    @PostMapping("/create-option")
    public String createOption(@ModelAttribute("option") CreateNewOptionRequest option, Model model){
        optionService.save(option);
        return "option_success";
    }

    @GetMapping("/update-option/{id}")
    public String showUpdateOptionForm(@PathVariable("id") long id, Model model){
        Option option = optionService.findById(id);
        model.addAttribute("option", option);
        return "update_option";
    }
    @PostMapping("/update-option/{id}")
    public String updateOption(@PathVariable("id") long id, UpdateExistingOptionRequest option, BindingResult result, Model model){
        if (result.hasErrors()) {
            option.setId(id);
            return "update_option";
        }
        optionService.update(option, id);
        Option newOption = optionService.findById(id);
        model.addAttribute("option", newOption);
        return "option_success";
    }

}
