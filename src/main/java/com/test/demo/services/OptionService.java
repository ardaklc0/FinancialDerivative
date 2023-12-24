package com.test.demo.services;
import com.test.demo.dto.request.option.CreateNewOptionRequest;
import com.test.demo.dto.request.option.UpdateExistingOptionRequest;
import com.test.demo.model.Option;
import com.test.demo.repository.OptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OptionService{
    @Autowired
    private OptionRepository optionRepository;
    @Transactional
    public void save(CreateNewOptionRequest option){
        Option newOption = new Option();
        newOption.setIsCall(option.getIsCall());
        newOption.setStrikePrice(option.getStrikePrice());
        newOption.setRiskFreeInterestValue(option.getRiskFreeInterestValue());
        newOption.setUpRate(option.getUpRate());
        newOption.setDownRate(option.getDownRate());
        newOption.setExerciseTime(option.getExerciseTime());
        newOption.setStockPrice(option.getStockPrice());
        newOption.setBondPrice(option.getBondPrice());
        newOption.setProbabilityOfUp(option.getProbabilityOfUp());
        optionRepository.save(newOption);
    }
    @Transactional
    public void update(UpdateExistingOptionRequest request, Long id){
        Optional<Option> optionalOption = optionRepository.findById(id);
        if (optionalOption.isPresent()){
            Option existingOption = optionalOption.get();
            existingOption.setIsCall(request.getIsCall());
            existingOption.setStrikePrice(request.getStrikePrice());
            existingOption.setRiskFreeInterestValue(request.getRiskFreeInterestValue());
            existingOption.setUpRate(request.getUpRate());
            existingOption.setDownRate(request.getDownRate());
            existingOption.setExerciseTime(request.getExerciseTime());
            existingOption.setStockPrice(request.getStockPrice());
            existingOption.setBondPrice(request.getBondPrice());
            existingOption.setProbabilityOfUp(request.getProbabilityOfUp());
            optionRepository.save(existingOption);
        }
    }
    public void delete(Long id){
        optionRepository.deleteById(id);
    }
    public List<Option> findAll(){
        return optionRepository.findAll();
    }
    public Option findById(Long id){
        Optional<Option> optionalOption = optionRepository.findById(id);
        return optionalOption.orElseThrow(() -> new IllegalArgumentException("Invalid option Id:" + id));
    }

    public long calculateUpValue(long stockPrice, double upRate){
        return (long) (stockPrice * (1 + upRate));
    }
    public long calculateDownValue(long stockPrice, double downRate){
        return (long) (stockPrice * (1 - downRate));
    }
    public long calculateFutureBond(long bondPrice, double riskFreeInterestValue){
        return (long) (bondPrice * (1 + riskFreeInterestValue));
    }
    public long calculateUpPortfolio(long stockPrice, long bondPrice, double upRate, double riskFreeInterestValue){
        return (long) (stockPrice * (1 + upRate) + bondPrice * (1 + riskFreeInterestValue));
    }
    public long calculateDownPortfolio(long stockPrice, long bondPrice, double downRate, double riskFreeInterestValue){
        return (long) (stockPrice * (1 - downRate) + bondPrice * (1 + riskFreeInterestValue));
    }
    public long calculateLayOff(long strikePrice, long stockPrice, double upRate){
        long stockUpPrice = calculateUpValue(stockPrice, upRate);
        return stockUpPrice - strikePrice;
    }
    public double calculateStockShare(long stockPrice, double upRate, double downRate, long strikePrice){
        long stockUpPrice = calculateUpValue(stockPrice, upRate);
        long stockDownPrice = calculateDownValue(stockPrice, downRate);
        return (double) (stockUpPrice - strikePrice) / (stockUpPrice - stockDownPrice);
    }
    public double calculateBondShare(long stockPrice, double upRate, double downRate, long StrikePrice, long bondPrice, double riskFreeInterestValue){
        double stockShare = calculateStockShare(stockPrice, upRate, downRate, StrikePrice);
        long futureBond = calculateFutureBond(bondPrice, riskFreeInterestValue);
        long stockDownPrice = calculateDownValue(stockPrice, downRate);
        return -(stockShare * ((double) stockDownPrice / futureBond));
    }
    //TODO 01: Bug Fix
    public double replicateThePortfolio(long stockPrice, double upRate, double downRate, long StrikePrice, long bondPrice, double riskFreeInterestValue){
        double stockShare = calculateStockShare(stockPrice, upRate, downRate, StrikePrice);
        double bondShare = calculateBondShare(stockPrice, upRate, downRate, StrikePrice, bondPrice, riskFreeInterestValue);
        return stockShare * stockPrice + bondShare * bondPrice;
    }

}
