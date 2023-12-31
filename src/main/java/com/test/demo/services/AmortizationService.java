package com.test.demo.services;

import com.test.demo.dto.request.amortization.CreateNewAmortizationRequest;
import com.test.demo.dto.request.amortization.UpdateExistingAmortizationRequest;
import com.test.demo.model.Amortization;
import com.test.demo.repository.AmortizationRepository;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class AmortizationService implements IService<Amortization, CreateNewAmortizationRequest, UpdateExistingAmortizationRequest>{
    @Autowired
    private AmortizationRepository amortizationRepository;
    @Override
    public Amortization save(CreateNewAmortizationRequest request) {
        Amortization newAmortization = new Amortization();
        newAmortization.setCouponRate(request.getCouponRate());
        newAmortization.setYieldRate(request.getYieldRate());
        newAmortization.setFaceValue(request.getFaceValue());
        newAmortization.setPaymentPeriod(request.getPaymentPeriod());
        newAmortization.setTotalPaymentYear(request.getTotalPaymentYear());
        newAmortization.setCouponValue(request.getCouponValue());
        return amortizationRepository.save(newAmortization);
    }

    @Override
    public void update(UpdateExistingAmortizationRequest request, Long id) {
        Optional<Amortization> optionalAmortization = amortizationRepository.findById(id);
        if (optionalAmortization.isPresent()) {
            Amortization existingAmortization = optionalAmortization.get();
            existingAmortization.setCouponRate(request.getCouponRate());
            existingAmortization.setYieldRate(request.getYieldRate());
            existingAmortization.setFaceValue(request.getFaceValue());
            existingAmortization.setPaymentPeriod(request.getPaymentPeriod());
            existingAmortization.setTotalPaymentYear(request.getTotalPaymentYear());
            existingAmortization.setCouponValue(request.getCouponValue());
            amortizationRepository.save(existingAmortization);
        }
    }
    @Override
    public void delete(Long id) {
        amortizationRepository.deleteById(id);
    }
    @Override
    public List<Amortization> findAll() {
        return amortizationRepository.findAll();
    }
    @Override
    public Amortization findById(Long id) {
        Optional<Amortization> optionalAmortization = amortizationRepository.findById(id);
        return optionalAmortization.orElseThrow(() -> new IllegalArgumentException("Invalid amortization Id:" + id));
    }
    public double numberOfPaymentPeriods(int paymentPeriod, double totalPaymentYear){
        return paymentPeriod * totalPaymentYear;
    }
    public double ratePerPeriod(double couponRate, int paymentPeriod){
        return couponRate / paymentPeriod;
    }
    public double couponPayment(double faceValue, double couponRate, int paymentPeriod){
        double ratePerPeriod = ratePerPeriod(couponRate, paymentPeriod);
        return faceValue * ratePerPeriod;
    }
    public double annuityPresentFactor(double couponRate, int paymentPeriod, double totalPaymentYear){
        double ratePerPeriod = ratePerPeriod(couponRate, paymentPeriod);
        double numberOfPaymentPeriods = numberOfPaymentPeriods(paymentPeriod, totalPaymentYear);
        return (1 - Math.pow((1 + ratePerPeriod), -numberOfPaymentPeriods)) / (ratePerPeriod);
    }
    public double bondPrice(double faceValue, double couponRate, int paymentPeriod, double totalPaymentYear, double couponValue){
        double couponPayment = couponPayment(faceValue, couponRate, paymentPeriod);
        double annuityPresentFactor = annuityPresentFactor(couponRate, paymentPeriod, totalPaymentYear);
        double ratePerPeriod = ratePerPeriod(couponRate, paymentPeriod);
        double numberOfPaymentPeriods = numberOfPaymentPeriods(paymentPeriod, totalPaymentYear);
        return couponPayment * annuityPresentFactor + couponValue * Math.pow((1 + ratePerPeriod), -numberOfPaymentPeriods);
    }
    public double effectiveInterestEarned(double faceValue, double couponRate, int paymentPeriod, double totalPaymentYear, double couponValue, double yieldRate){
        double bondPrice = bondPrice(faceValue, couponRate, paymentPeriod, totalPaymentYear, couponValue);
        return bondPrice * yieldRate;
    }
    public double amortizedAmount(double bondPrice, double faceValue, double couponRate, int paymentPeriod, double totalPaymentYear, double couponValue, double yieldRate){
        double effectiveInterestEarned = effectiveInterestEarned(faceValue, couponRate, paymentPeriod, totalPaymentYear, couponValue, yieldRate);
        return bondPrice - effectiveInterestEarned;
    }

    public Map<String, Object> calculateAmortizationSchedule(double faceValue, double couponRate, int paymentPeriod, double totalPaymentYear, double couponValue, double yieldRate){
        double totalPaymentPeriod = numberOfPaymentPeriods(paymentPeriod, totalPaymentYear);
        Map<String, Object> amortizationSchedule = new HashMap<>();
        double bondPrice = bondPrice(faceValue, couponRate, paymentPeriod, totalPaymentYear, couponValue);
        double effectiveInterestEarned = effectiveInterestEarned(faceValue, couponRate, paymentPeriod, totalPaymentYear, couponValue, yieldRate);
        double amortizedAmount = amortizedAmount(bondPrice, faceValue, couponRate, paymentPeriod, totalPaymentYear, couponValue, yieldRate);
        for (int i = 0; i < totalPaymentPeriod + 1; i++) {
            Map<String, Object> amortizationScheduleRow = new HashMap<>();
            amortizationScheduleRow.put("bondPrice", bondPrice);
            amortizationScheduleRow.put("effectiveInterestEarned", effectiveInterestEarned);
            amortizationScheduleRow.put("amortizedAmount", amortizedAmount);
            amortizationSchedule.put("row" + i, amortizationScheduleRow);
            bondPrice = bondPrice - amortizedAmount;
            effectiveInterestEarned = bondPrice * yieldRate;
            amortizedAmount = bondPrice - effectiveInterestEarned;
        }
        return amortizationSchedule;
    }
}
