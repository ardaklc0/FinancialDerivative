package com.test.demo.viewmodels;

import com.test.demo.model.Amortization;
import com.test.demo.services.AmortizationService;

import java.util.Map;

public class AmortizationModel extends IModel<Amortization, AmortizationService>{
    public AmortizationModel(Amortization model, AmortizationService service) {
        super(model, service);
    }
    @Override
    public Map<String, Object> calculateAllValues(){
        return Map.of();
    }
}
