package com.test.demo.viewmodels;

import com.test.demo.model.Option;
import com.test.demo.services.OptionService;

import java.util.Map;

public class OptionModel extends IModel<Option, OptionService> {

    public OptionModel(Option model, OptionService service) {
        super(model, service);
    }

    @Override
    public Map<String, Object> calculateAllValues(){
        return Map.of(
        "stockUpValue", service.calculateUpValue(model.getStockPrice(), model.getUpRate()),
        "stockDownValue", service.calculateDownValue(model.getStockPrice(), model.getDownRate()),
        "bondFutureValue", service.calculateFutureBond(model.getBondPrice(), model.getRiskFreeInterestValue()),
        "upPortfolioValue", service.calculateUpPortfolio(model.getStockPrice(), model.getBondPrice(), model.getUpRate(), model.getRiskFreeInterestValue()),
        "downPortfolioValue", service.calculateDownPortfolio(model.getStockPrice(), model.getBondPrice(), model.getDownRate(), model.getRiskFreeInterestValue()),
        "layOff", service.calculateLayOff(model.getStrikePrice(), model.getStockPrice(), model.getUpRate()),
        "stockShare", service.calculateStockShare(model.getStockPrice(), model.getUpRate(), model.getDownRate(), model.getStrikePrice()),
        "bondShare", service.calculateBondShare(model.getBondPrice(), model.getUpRate(), model.getDownRate(), model.getStrikePrice(), model.getBondPrice(), model.getRiskFreeInterestValue()),
        "replicatingPortfolioValue", service.replicateThePortfolio(model.getStockPrice(), model.getUpRate(), model.getDownRate(), model.getStrikePrice(), model.getBondPrice(), model.getRiskFreeInterestValue())
        );
    }
}
