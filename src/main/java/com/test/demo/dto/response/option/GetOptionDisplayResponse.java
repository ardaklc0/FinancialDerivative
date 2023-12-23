package com.test.demo.dto.response.option;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class GetOptionDisplayResponse {
    private Long id;
    private boolean isCall;
    private Long strikePrice;
    private Double riskFreeInterestValue;
    private Double upRate;
    private Double downRate;
    private int exerciseTime;
    private Long stockPrice;
    private Long bondPrice;
    private Double probabilityOfUp;

    public Long getId() {
        return id;
    }

    public void setId(Long option_id) {
        this.id = option_id;
    }

    public boolean getIsCall() {
        return isCall;
    }

    public void setIsCall(boolean option_is_call) {
        this.isCall = option_is_call;
    }

    public Long getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(Long option_strike_price) {
        this.strikePrice = option_strike_price;
    }

    public Double getRiskFreeInterestValue() {
        return riskFreeInterestValue;
    }

    public void setRiskFreeInterestValue(Double optionRiskFreeInterestValue) {
        this.riskFreeInterestValue = optionRiskFreeInterestValue;
    }

    public Double getUpRate() {
        return upRate;
    }

    public void setUpRate(Double option_up_rate) {
        this.upRate = option_up_rate;
    }

    public Double getDownRate() {
        return downRate;
    }

    public void setDownRate(Double option_down_rate) {
        this.downRate = option_down_rate;
    }

    public int getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(int exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public Long getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Long option_stock_price) {
        this.stockPrice = option_stock_price;
    }

    public Long getBondPrice() {
        return bondPrice;
    }

    public void setBondPrice(Long option_bond_price) {
        this.bondPrice = option_bond_price;
    }

    public Double getProbabilityOfUp() {
        return probabilityOfUp;
    }

    public void setProbabilityOfUp(Double option_probability_of_up) {
        this.probabilityOfUp = option_probability_of_up;
    }
}
