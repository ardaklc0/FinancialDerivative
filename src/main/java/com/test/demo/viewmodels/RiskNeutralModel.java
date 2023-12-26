package com.test.demo.viewmodels;

import com.test.demo.model.RiskNeutral;
import com.test.demo.services.RiskNeutralService;

import java.util.Map;

public class RiskNeutralModel extends IModel<RiskNeutral, RiskNeutralService>{
    public RiskNeutralModel(RiskNeutral model, RiskNeutralService service) {
        super(model, service);
    }
    @Override
    public Map<String, Object> calculateAllValues(){
        return Map.of(
        "stockUpValue", service.calculateUpValue(model.getStockPrice(), model.getUpRate()),
        "stockDownValue", service.calculateDownValue(model.getStockPrice(), model.getDownRate()),
        "layOff", service.calculateLayOff(model.getStrikePrice(), model.getStockPrice(), model.getUpRate()),
        "riskNeutralProbability", service.calculateRiskNeutralProbability(model.getUpRate(), model.getDownRate(), model.getRiskFreeInterestValue()),
        "riskNeutralExpectedReturn", service.calculateRiskNeutralExpectedReturn(model.getUpRate(), model.getDownRate(), model.getRiskFreeInterestValue(), model.getStrikePrice(), model.getStockPrice()),
        "optionPrice", service.calculateOptionPrice(model.getUpRate(), model.getDownRate(), model.getRiskFreeInterestValue(), model.getStrikePrice(), model.getStockPrice())
        );
    }
}
