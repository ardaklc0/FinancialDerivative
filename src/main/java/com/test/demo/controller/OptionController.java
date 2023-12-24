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
        model.addAttribute("stockUpValue", optionService.calculateUpValue(option.getStockPrice(), option.getUpRate()));
        model.addAttribute("stockDownValue", optionService.calculateDownValue(option.getStockPrice(), option.getDownRate()));
        model.addAttribute("bondFutureValue", optionService.calculateFutureBond(option.getBondPrice(), option.getRiskFreeInterestValue()));
        model.addAttribute("upPortfolioValue", optionService.calculateUpPortfolio(option.getStockPrice(), option.getBondPrice(), option.getUpRate(), option.getRiskFreeInterestValue()));
        model.addAttribute("downPortfolioValue", optionService.calculateDownPortfolio(option.getStockPrice(), option.getBondPrice(), option.getDownRate(), option.getRiskFreeInterestValue()));
        model.addAttribute("layOff", optionService.calculateLayOff(option.getStrikePrice(), option.getStockPrice(), option.getUpRate()));
        model.addAttribute("stockShare", optionService.calculateStockShare(option.getStockPrice(), option.getUpRate(), option.getDownRate(), option.getStrikePrice()));
        model.addAttribute("bondShare", optionService.calculateBondShare(option.getBondPrice(), option.getUpRate(), option.getDownRate(), option.getStrikePrice(), option.getBondPrice(), option.getRiskFreeInterestValue()));
        model.addAttribute("replicatingPortfolioValue", optionService.replicateThePortfolio(option.getStockPrice(), option.getUpRate(), option.getDownRate(), option.getStrikePrice(), option.getBondPrice(), option.getRiskFreeInterestValue()));
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
        model.addAttribute("stockUpValue", optionService.calculateUpValue(newOption.getStockPrice(), newOption.getUpRate()));
        model.addAttribute("stockDownValue", optionService.calculateDownValue(newOption.getStockPrice(), newOption.getDownRate()));
        model.addAttribute("bondFutureValue", optionService.calculateFutureBond(newOption.getBondPrice(), newOption.getRiskFreeInterestValue()));
        model.addAttribute("upPortfolioValue", optionService.calculateUpPortfolio(newOption.getStockPrice(), newOption.getBondPrice(), newOption.getUpRate(), newOption.getRiskFreeInterestValue()));
        model.addAttribute("downPortfolioValue", optionService.calculateDownPortfolio(newOption.getStockPrice(), newOption.getBondPrice(), newOption.getDownRate(), newOption.getRiskFreeInterestValue()));
        model.addAttribute("layOff", optionService.calculateLayOff(newOption.getStrikePrice(), newOption.getStockPrice(), newOption.getUpRate()));
        model.addAttribute("stockShare", optionService.calculateStockShare(newOption.getStockPrice(), newOption.getUpRate(), newOption.getDownRate(), newOption.getStrikePrice()));
        model.addAttribute("bondShare", optionService.calculateBondShare(newOption.getBondPrice(), newOption.getUpRate(), newOption.getDownRate(), newOption.getStrikePrice(), newOption.getBondPrice(), newOption.getRiskFreeInterestValue()));
        model.addAttribute("replicatingPortfolioValue", optionService.replicateThePortfolio(newOption.getStockPrice(), newOption.getUpRate(), newOption.getDownRate(), newOption.getStrikePrice(), newOption.getBondPrice(), newOption.getRiskFreeInterestValue()));
        return "option_success";
    }

}
