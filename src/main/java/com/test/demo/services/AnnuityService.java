package com.test.demo.services;

import com.test.demo.builder.AnnuityBuilder;
import com.test.demo.dto.request.annuity.CreateNewAnnuityRequest;
import com.test.demo.dto.request.annuity.UpdateExistingAnnuityRequest;
import com.test.demo.model.Annuity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.demo.repository.AnnuityRepository;
import java.util.List;
import java.util.Optional;
import com.test.demo.builder.AnnuityBuilder;

@Service
public class AnnuityService implements IService<Annuity, CreateNewAnnuityRequest, UpdateExistingAnnuityRequest> {
    @Autowired
    private AnnuityRepository annuityRepository;
    @Override
    @Transactional
    public Annuity save(CreateNewAnnuityRequest request) {
        Annuity newAnnuity = new AnnuityBuilder().setBorrowedLoan(0.0).setWannaSave(0.0).build();
        newAnnuity.setPaymentPeriod(request.getPaymentPeriod());
        newAnnuity.setRateOfInterest(request.getRateOfInterest());
        newAnnuity.setTotalPaymentYear(request.getTotalPaymentYear());
        if (request.getBorrowedLoan() != null) {
            newAnnuity.setBorrowedLoan(request.getBorrowedLoan());
        }
        if (request.getWannaSave() != null) {
            newAnnuity.setWannaSave(request.getWannaSave());
        }
        return annuityRepository.save(newAnnuity);
    }
    //TODO 01: WHAT THE FUCK IS YOUR PROBLEM?
    @Override
    public void update(UpdateExistingAnnuityRequest request, Long id) {
        Optional<Annuity> optionalAnnuity = annuityRepository.findById(id);
        if (optionalAnnuity.isPresent()) {
            Annuity existingAnnuity = optionalAnnuity.get();
            existingAnnuity.setBorrowedLoan(request.getBorrowedLoan());
            existingAnnuity.setPaymentPeriod(request.getPaymentPeriod());
            existingAnnuity.setWannaSave(request.getWannaSave());
            existingAnnuity.setRateOfInterest(request.getRateOfInterest());
            existingAnnuity.setTotalPaymentYear(request.getTotalPaymentYear());
            annuityRepository.save(existingAnnuity);
        }
    }
    @Override
    public void delete(Long id) {
        annuityRepository.deleteById(id);
    }
    @Override
    public List<Annuity> findAll() {
        return annuityRepository.findAll();
    }
    @Override
    public Annuity findById(Long id) {
        Optional<Annuity> optionalAnnuity = annuityRepository.findById(id);
        return optionalAnnuity.orElseThrow(() -> new IllegalArgumentException("Invalid annuity Id:" + id));
    }
    public double numberOfPaymentPeriods(int paymentPeriod, double totalPaymentYear){
        return paymentPeriod * totalPaymentYear;
    }
    public double ratePerPeriod(double rateOfInterest, int paymentPeriod){
        return rateOfInterest / paymentPeriod;
    }
    public double annuityPresentFactor(double rateOfInterest, int paymentPeriod, double totalPaymentYear){
        double yieldRate = ratePerPeriod(rateOfInterest, paymentPeriod);
        double numberOfPaymentPeriods = numberOfPaymentPeriods(paymentPeriod, totalPaymentYear);
        return (1 - Math.pow((1 + yieldRate), -numberOfPaymentPeriods)) / (yieldRate);
    }
    public double annuityFutureFactor(double rateOfInterest, int paymentPeriod, double totalPaymentYear){
        double yieldRate = ratePerPeriod(rateOfInterest, paymentPeriod);
        double numberOfPaymentPeriods = numberOfPaymentPeriods(paymentPeriod, totalPaymentYear);
        return (Math.pow((1 + yieldRate), numberOfPaymentPeriods) - 1) / (yieldRate);
    }
    public double installmentToPay(double borrowedLoan, double rateOfInterest, int paymentPeriod, double totalPaymentYear){
        double annuityPresentFactor = annuityPresentFactor(rateOfInterest, paymentPeriod, totalPaymentYear);
        return borrowedLoan / annuityPresentFactor;
    }
    public double installmentToSave(double wannaSave, double rateOfInterest, int paymentPeriod, double totalPaymentYear){
        double annuityFutureFactor = annuityFutureFactor(rateOfInterest, paymentPeriod, totalPaymentYear);
        return wannaSave / annuityFutureFactor;
    }

}
