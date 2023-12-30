package com.test.demo.controller;

import com.test.demo.dto.request.amortization.CreateNewAmortizationRequest;
import com.test.demo.dto.request.amortization.UpdateExistingAmortizationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.test.demo.services.AmortizationService;
import com.test.demo.model.Amortization;

import java.util.Map;

@Controller
@RequestMapping("/amortization")
public class AmortizationController {
    @Autowired
    private AmortizationService amortizationService;
    @GetMapping("/amortization-home")
    public String amortization(Model model, Authentication authentication){
        model.addAttribute("user", authentication.getPrincipal());
        return "amortization/amortization";
    }
    @GetMapping("/create-amortization")
    public String showCreateAmortizationForm(Model model){
        model.addAttribute("amortization", new CreateNewAmortizationRequest());
        return "amortization/create_amortization";
    }
    @PostMapping("/create-amortization")
    public String createAmortization(CreateNewAmortizationRequest amortization, Model model){
        Amortization newAmortization = amortizationService.save(amortization);
        Map<String, Object> bondAmortizationSchedule = amortizationService.calculateAmortizationSchedule(
            newAmortization.getFaceValue(),
            newAmortization.getCouponRate(),
            newAmortization.getPaymentPeriod(),
            newAmortization.getTotalPaymentYear(),
            newAmortization.getCouponValue(),
            newAmortization.getYieldRate()
        );
        model.addAttribute("amortization", newAmortization);
        model.addAttribute("bondAmortizationSchedule", bondAmortizationSchedule);
        return "amortization/amortization_success";
    }
    @GetMapping("/update-amortization/{id}")
    public String showUpdateAmortizationForm(@PathVariable("id") long id, Model model){
        Amortization amortization = amortizationService.findById(id);
        model.addAttribute("amortization", amortization);
        return "amortization/update_amortization";
    }
    @PostMapping("/update-amortization/{id}")
    public String updateAmortization(@PathVariable("id") long id, UpdateExistingAmortizationRequest amortization, BindingResult result, Model model){
        if (result.hasErrors()){
            amortization.setId(id);
            return "amortization/update_amortization";
        }
        amortizationService.update(amortization, id);
        Amortization newAmortization = amortizationService.findById(id);
        Map<String, Object> bondAmortizationSchedule = amortizationService.calculateAmortizationSchedule(
                newAmortization.getFaceValue(),
                newAmortization.getCouponRate(),
                newAmortization.getPaymentPeriod(),
                newAmortization.getTotalPaymentYear(),
                newAmortization.getCouponValue(),
                newAmortization.getYieldRate()
        );
        model.addAttribute("amortization", newAmortization);
        model.addAttribute("bondAmortizationSchedule", bondAmortizationSchedule);
        return "amortization/amortization_success";
    }
    @PostMapping("/delete-amortization/{id}")
    public String deleteAmortization(@PathVariable("id") long id) {
        amortizationService.delete(id);
        return "amortization/show_all_amortizations";
    }
    @GetMapping("/amortization-list")
    public String amortizationList(Model model){
        model.addAttribute("amortizations", amortizationService.findAll());
        return "amortization/show_all_amortizations";
    }

}
