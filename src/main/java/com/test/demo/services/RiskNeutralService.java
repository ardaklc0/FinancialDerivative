package com.test.demo.services;

import com.test.demo.dto.request.riskneutral.CreateNewRiskNeutralRequest;
import com.test.demo.dto.request.riskneutral.UpdateExistingRiskNeutralRequest;
import com.test.demo.model.RiskNeutral;
import com.test.demo.repository.RiskNeutralRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskNeutralService implements IFinanceService, IService<RiskNeutral, CreateNewRiskNeutralRequest, UpdateExistingRiskNeutralRequest>{
    @Autowired
    private RiskNeutralRepository riskNeutralRepository;
    @Transactional
    public RiskNeutral save(CreateNewRiskNeutralRequest riskneutral){
        RiskNeutral newRiskNeutral = new RiskNeutral();
        newRiskNeutral.setIsCall(riskneutral.getIsCall());
        newRiskNeutral.setStrikePrice(riskneutral.getStrikePrice());
        newRiskNeutral.setRiskFreeInterestValue(riskneutral.getRiskFreeInterestValue());
        newRiskNeutral.setUpRate(riskneutral.getUpRate());
        newRiskNeutral.setDownRate(riskneutral.getDownRate());
        newRiskNeutral.setExerciseTime(riskneutral.getExerciseTime());
        newRiskNeutral.setStockPrice(riskneutral.getStockPrice());
        newRiskNeutral.setProbabilityOfUp(riskneutral.getProbabilityOfUp());
        return riskNeutralRepository.save(newRiskNeutral);
    }
    @Transactional
    public void update(UpdateExistingRiskNeutralRequest request, Long id){
        RiskNeutral existingRiskNeutral = riskNeutralRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid riskneutral Id:" + id));
        existingRiskNeutral.setIsCall(request.getIsCall());
        existingRiskNeutral.setStrikePrice(request.getStrikePrice());
        existingRiskNeutral.setRiskFreeInterestValue(request.getRiskFreeInterestValue());
        existingRiskNeutral.setUpRate(request.getUpRate());
        existingRiskNeutral.setDownRate(request.getDownRate());
        existingRiskNeutral.setExerciseTime(request.getExerciseTime());
        existingRiskNeutral.setStockPrice(request.getStockPrice());
        existingRiskNeutral.setProbabilityOfUp(request.getProbabilityOfUp());
        riskNeutralRepository.save(existingRiskNeutral);
    }
    public void delete(Long id){
        riskNeutralRepository.deleteById(id);
    }
    public List<RiskNeutral> findAll(){
        return riskNeutralRepository.findAll();
    }
    public RiskNeutral findById(Long id){
        return riskNeutralRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid riskneutral Id:" + id));
    }
    public long calculateUpValue(long stockPrice, double upRate){
        return (long) (stockPrice * (1 + upRate));
    }
    public long calculateDownValue(long stockPrice, double downRate){
        return (long) (stockPrice * (1 + downRate));
    }
    public long calculateFutureBond(long bondPrice, double riskFreeInterestValue){
        return (long) (bondPrice * (1 + riskFreeInterestValue));
    }
    public long calculateUpPortfolio(long stockPrice, long bondPrice, double upRate, double riskFreeInterestValue){
        return (long) (stockPrice * (1 + upRate) + bondPrice * (1 + riskFreeInterestValue));
    }
    public long calculateDownPortfolio(long stockPrice, long bondPrice, double downRate, double riskFreeInterestValue){
        return (long) (stockPrice * (1 + downRate) + bondPrice * (1 + riskFreeInterestValue));
    }
    public long calculateLayOff(long strikePrice, long stockPrice, double upRate){
        long stockUpPrice = calculateUpValue(stockPrice, upRate);
        return stockUpPrice - strikePrice;
    }
    public double calculateRiskNeutralProbability(double upRate, double downRate, double riskFreeInterestValue){
        return (riskFreeInterestValue - downRate) / (upRate - downRate);
    }
    public double calculateRiskNeutralExpectedReturn(double upRate, double downRate, double riskFreeInterestValue, long strikePrice, long stockPrice){
        double riskNeutralProbability = calculateRiskNeutralProbability(upRate, downRate, riskFreeInterestValue);
        long layOff = calculateLayOff(strikePrice, stockPrice, upRate);
        return (layOff * riskNeutralProbability);
    }
    public double calculateOptionPrice(double upRate, double downRate, double riskFreeInterestValue, long strikePrice, long stockPrice){
        double riskNeutralExpectedReturn = calculateRiskNeutralExpectedReturn(upRate, downRate, riskFreeInterestValue, strikePrice, stockPrice);
        return riskNeutralExpectedReturn / (1 + riskFreeInterestValue);
    }
}
