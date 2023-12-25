package com.test.demo.viewmodels;

import com.test.demo.model.Option;
import com.test.demo.repository.OptionRepository;
import com.test.demo.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;

public class OptionModel implements IModel {
    @Autowired
    private OptionService optionService;
    private final Option option;

    public OptionModel(Option option, OptionService optionService){
        this.option = option;
        this.optionService = optionService;
    }

    public Map<String, Object> calculateAllValues(){
        return Map.of(
        "stockUpValue", optionService.calculateUpValue(option.getStockPrice(), option.getUpRate()),
        "stockDownValue", optionService.calculateDownValue(option.getStockPrice(), option.getDownRate()),
        "bondFutureValue", optionService.calculateFutureBond(option.getBondPrice(), option.getRiskFreeInterestValue()),
        "upPortfolioValue", optionService.calculateUpPortfolio(option.getStockPrice(), option.getBondPrice(), option.getUpRate(), option.getRiskFreeInterestValue()),
        "downPortfolioValue", optionService.calculateDownPortfolio(option.getStockPrice(), option.getBondPrice(), option.getDownRate(), option.getRiskFreeInterestValue()),
        "layOff", optionService.calculateLayOff(option.getStrikePrice(), option.getStockPrice(), option.getUpRate()),
        "stockShare", optionService.calculateStockShare(option.getStockPrice(), option.getUpRate(), option.getDownRate(), option.getStrikePrice()),
        "bondShare", optionService.calculateBondShare(option.getBondPrice(), option.getUpRate(), option.getDownRate(), option.getStrikePrice(), option.getBondPrice(), option.getRiskFreeInterestValue()),
        "replicatingPortfolioValue", optionService.replicateThePortfolio(option.getStockPrice(), option.getUpRate(), option.getDownRate(), option.getStrikePrice(), option.getBondPrice(), option.getRiskFreeInterestValue())
        );
    }
}
