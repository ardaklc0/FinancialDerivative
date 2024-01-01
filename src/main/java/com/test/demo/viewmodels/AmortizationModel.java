package com.test.demo.viewmodels;

import com.test.demo.model.Amortization;
import com.test.demo.services.AmortizationService;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

public class AmortizationModel extends IModel<Amortization, AmortizationService>{
    public AmortizationModel(Amortization model, AmortizationService service) {
        super(model, service);
    }
    @Override
    public Map<String, Object> calculateAllValues(){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
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
            "bondPrice", Double.parseDouble(df.format(
                service.bondPrice(
                    model.getFaceValue(),
                    model.getYieldRate(),
                    model.getCouponRate(),
                    model.getPaymentPeriod(),
                    model.getTotalPaymentYear(),
                    model.getCouponValue()
                )
            ))
        );
    }
}
