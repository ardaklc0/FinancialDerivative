package com.test.demo.viewmodels;

import com.test.demo.model.Annuity;
import com.test.demo.services.AnnuityService;

import java.util.Map;

public class AnnuityModel extends IModel<Annuity, AnnuityService> {
    public AnnuityModel(Annuity model, AnnuityService service) {
        super(model, service);
    }
    @Override
    public Map<String, Object> calculateAllValues(){
        return Map.of(
        "numberOfPaymentPeriods", service.numberOfPaymentPeriods(model.getPaymentPeriod(), model.getTotalPaymentYear()),
        "ratePerPeriod", service.ratePerPeriod(model.getRateOfInterest(), model.getPaymentPeriod()),
        "annuityPresentFactor", service.annuityPresentFactor(model.getRateOfInterest(), model.getPaymentPeriod(), model.getTotalPaymentYear()),
        "annuityFutureFactor", service.annuityFutureFactor(model.getRateOfInterest(), model.getPaymentPeriod(), model.getTotalPaymentYear()),
        "installmentToPay", service.installmentToPay(model.getBorrowedLoan(), model.getRateOfInterest(), model.getPaymentPeriod(), model.getTotalPaymentYear()),
        "installmentToSave", service.installmentToSave(model.getWannaSave(), model.getRateOfInterest(), model.getPaymentPeriod(), model.getTotalPaymentYear())
        );
    }
}
