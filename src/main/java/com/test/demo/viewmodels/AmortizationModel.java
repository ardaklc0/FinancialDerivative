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
        return Map.of(
            "amortizationSchedule", service.calculateAmortizationSchedule(
                model.getFaceValue(),
                model.getCouponRate(),
                model.getPaymentPeriod(),
                model.getTotalPaymentYear(),
                model.getCouponValue(),
                model.getYieldRate()
            ),
            "couponPayment", service.couponPayment(
                model.getFaceValue(),
                model.getCouponRate(),
                model.getPaymentPeriod()
            ),
            "bondPrice", service.bondPrice(
                model.getFaceValue(),
                model.getYieldRate(),
                model.getCouponRate(),
                model.getPaymentPeriod(),
                model.getTotalPaymentYear(),
                model.getCouponValue()
            )
        );
    }
}
