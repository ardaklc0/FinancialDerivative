package com.test.demo.controller;
import com.test.demo.dto.request.riskneutral.CreateNewRiskNeutralRequest;
import com.test.demo.dto.request.riskneutral.UpdateExistingRiskNeutralRequest;
import com.test.demo.model.RiskNeutral;
import com.test.demo.services.RiskNeutralService;
import com.test.demo.viewmodels.RiskNeutralModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping("/risk-neutral")
public class RiskNeutralController {
    @Autowired
    private RiskNeutralService riskNeutralService;
    @GetMapping("/risk-neutral-home")
    public String riskNeutral(Model model, Authentication authentication){
        model.addAttribute("user", authentication.getPrincipal());
        return "risk_neutral/risk_neutral";
    }
    @GetMapping("/create-risk-neutral")
    public String showCreateRiskNeutralForm(Model model){
        model.addAttribute("riskNeutral", new CreateNewRiskNeutralRequest());
        return "risk_neutral/create_risk_neutral";
    }
    @PostMapping("/create-risk-neutral")
    public String createRiskNeutral(@ModelAttribute("riskNeutral") CreateNewRiskNeutralRequest riskneutral, Model model){
        RiskNeutral newRiskNeutral = riskNeutralService.save(riskneutral);
        RiskNeutralModel riskNeutralModel = new RiskNeutralModel(newRiskNeutral, riskNeutralService);
        Map<String, Object> riskNeutralValues = riskNeutralModel.calculateAllValues();
        model.addAttribute("riskNeutralValues", riskNeutralValues);
        return "risk_neutral/risk_neutral_success";
    }
    @GetMapping("/update-risk-neutral/{id}")
    public String showUpdateRiskNeutralForm(@PathVariable("id") long id, Model model){
        RiskNeutral riskNeutral = riskNeutralService.findById(id);
        model.addAttribute("riskNeutral", riskNeutral);
        return "risk_neutral/update_risk_neutral";
    }
    @PostMapping("/update-risk-neutral/{id}")
    public String updateRiskNeutral(@PathVariable("id") long id, UpdateExistingRiskNeutralRequest riskNeutral, BindingResult result, Model model){
        if (result.hasErrors()) {
            riskNeutral.setId(id);
            return "risk_neutral/update_risk_neutral";
        }
        riskNeutralService.update(riskNeutral, id);
        RiskNeutral newRiskNeutral = riskNeutralService.findById(id);
        RiskNeutralModel riskNeutralModel = new RiskNeutralModel(newRiskNeutral, riskNeutralService);
        Map<String, Object> riskNeutralValues = riskNeutralModel.calculateAllValues();
        model.addAttribute("riskNeutral", newRiskNeutral);
        model.addAttribute("riskNeutralValues", riskNeutralValues);
        return "risk_neutral/risk_neutral_success";
    }
}
