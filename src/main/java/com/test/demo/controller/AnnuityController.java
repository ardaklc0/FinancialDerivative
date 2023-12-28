package com.test.demo.controller;

import com.test.demo.dto.request.annuity.CreateNewAnnuityRequest;
import com.test.demo.dto.request.annuity.UpdateExistingAnnuityRequest;
import com.test.demo.model.Annuity;
import com.test.demo.services.AnnuityService;
import com.test.demo.viewmodels.AnnuityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/annuity")
public class AnnuityController {
    @Autowired
    private AnnuityService annuityService;
    @GetMapping("/annuity-home")
    public String annuity(Model model, Authentication authentication){
        model.addAttribute("user", authentication.getPrincipal());
        return "annuity/annuity";
    }
    @GetMapping("/create-annuity")
    public String showCreateAnnuityForm(Model model){
        model.addAttribute("annuity", new CreateNewAnnuityRequest());
        return "annuity/create_annuity";
    }
    @PostMapping("/create-annuity")
    public String createAnnuity(@ModelAttribute("annuity") CreateNewAnnuityRequest annuity, Model model){
        Annuity newAnnuity = annuityService.save(annuity);
        AnnuityModel annuityModel = new AnnuityModel(newAnnuity, annuityService);
        Map<String, Object> annuityValues = annuityModel.calculateAllValues();
        model.addAttribute("annuityValues", annuityValues);
        model.addAttribute("annuity", newAnnuity);
        return "annuity/annuity_success";
    }
    @GetMapping("/update-annuity/{id}")
    public String showUpdateAnnuityForm(@PathVariable("id") long id, Model model){
        Annuity annuity = annuityService.findById(id);
        model.addAttribute("annuity", annuity);
        return "annuity/update_annuity";
    }
    @PostMapping("/update-annuity/{id}")
    public String updateAnnuity(@PathVariable("id") long id, UpdateExistingAnnuityRequest annuity, BindingResult result, Model model){
        if (result.hasErrors()) {
            annuity.setId(id);
            return "annuity/update_annuity";
        }
        annuityService.update(annuity, id);
        Annuity newAnnuity = annuityService.findById(id);
        AnnuityModel annuityModel = new AnnuityModel(newAnnuity, annuityService);
        Map<String, Object> annuityValues = annuityModel.calculateAllValues();
        model.addAttribute("annuity", newAnnuity);
        model.addAttribute("annuityValues", annuityValues);
        return "annuity/annuity_success";
    }
}
