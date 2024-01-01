package com.test.demo.services;

import com.test.demo.dto.request.amortization.CreateNewAmortizationRequest;
import com.test.demo.dto.request.amortization.UpdateExistingAmortizationRequest;
import com.test.demo.model.Amortization;
import com.test.demo.repository.AmortizationRepository;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

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
    public double someRatePerPeriod(double someRate, int paymentPeriod){
        return someRate / paymentPeriod;
    }

    //F * r = Face Value * rate per period
    public double couponPayment(double faceValue, double couponRate, int paymentPeriod){
        double couponRatePerPeriod = someRatePerPeriod(couponRate, paymentPeriod);
        return faceValue * couponRatePerPeriod;
    }

    public double annuityPresentFactor(double yieldRate, int paymentPeriod, double totalPaymentYear){
        double yieldRatePerPeriod = someRatePerPeriod(yieldRate, paymentPeriod);
        double numberOfPaymentPeriods = numberOfPaymentPeriods(paymentPeriod, totalPaymentYear);
        return (1 - Math.pow((1 + yieldRatePerPeriod), -numberOfPaymentPeriods)) / (yieldRatePerPeriod);
    }
    public double couponVFactor(double yieldRate, int paymentPeriod, double totalPaymentYear){
        double yieldRatePerPeriod = someRatePerPeriod(yieldRate, paymentPeriod);
        double numberOfPaymentPeriods = numberOfPaymentPeriods(paymentPeriod, totalPaymentYear);
        return Math.pow((1 + yieldRatePerPeriod), -numberOfPaymentPeriods);
    }
    public double bondPrice(double faceValue, double yieldRate,double couponRate, int paymentPeriod, double totalPaymentYear, double couponValue){
        double couponPayment = couponPayment(faceValue, couponRate, paymentPeriod);
        double annuityPresentFactor = annuityPresentFactor(yieldRate, paymentPeriod, totalPaymentYear);
        double couponValueFactor = couponVFactor(yieldRate, paymentPeriod, totalPaymentYear);
        return couponPayment * annuityPresentFactor + couponValue * couponValueFactor;
    }
    public double effectiveInterestEarned(double bondPrice, double ratePerPeriod){
        return bondPrice * ratePerPeriod;
    }
    public double amortizedAmount(double bondPrice, double effectiveInterestEarned){
        return bondPrice - effectiveInterestEarned;
    }
    public Map<String, Object> calculateAmortizationSchedule(double faceValue, double couponRate, int paymentPeriod, double totalPaymentYear, double couponValue, double yieldRate){
        Map<String, Object> amortizationSchedule = new LinkedHashMap<>();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        double totalPaymentPeriod = numberOfPaymentPeriods(paymentPeriod, totalPaymentYear);
        double yieldRatePerPeriod = someRatePerPeriod(yieldRate, paymentPeriod);
        double couponPayment = couponPayment(faceValue, couponRate, paymentPeriod);
        double bondPrice = bondPrice(faceValue, yieldRate, couponRate, paymentPeriod, totalPaymentYear, couponValue);
        double effectiveInterestEarned = effectiveInterestEarned(bondPrice, yieldRatePerPeriod);
        double amortizedAmount = amortizedAmount(couponPayment, effectiveInterestEarned);
        for (int i = 0; i < totalPaymentPeriod; i++) {
            bondPrice = bondPrice - amortizedAmount;
            Map<String, Object> amortizationScheduleRow = new LinkedHashMap<>();
            amortizationScheduleRow.put("rowId", i + 1);
            amortizationScheduleRow.put("bondPrice", Double.parseDouble(df.format(bondPrice)));
            amortizationScheduleRow.put("effectiveInterestEarned", Double.parseDouble(df.format(effectiveInterestEarned)));
            amortizationScheduleRow.put("amortizedAmount", Double.parseDouble(df.format(amortizedAmount)));
            amortizationSchedule.put("row" + i, amortizationScheduleRow);
            effectiveInterestEarned = bondPrice * yieldRatePerPeriod;
            amortizedAmount = couponPayment - effectiveInterestEarned;
        }
        return amortizationSchedule;
    }
}
