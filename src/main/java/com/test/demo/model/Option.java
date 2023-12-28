package com.test.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "option")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "is_call")
    private boolean isCall;
    @Column(name = "strike_price")
    private Long strikePrice;
    @Column(name = "risk_free_interest_value")
    private Double riskFreeInterestValue;
    @Column(name = "up_rate")
    private Double upRate;
    @Column(name = "down_rate")
    private Double downRate;
    @Column(name = "exercise_time")
    private int exerciseTime;
    @Column(name = "stock_price")
    private Long stockPrice;
    @Column(name = "bond_price")
    private Long bondPrice;
    @Column(name = "probability_of_up", nullable = true)
    private Double probabilityOfUp;
    public Option(boolean isCall, Long strikePrice, Double riskFreeInterestValue, Double upRate, Double downRate, int exerciseTime, Long stockPrice, Long bondPrice, Double probabilityOfUp){
        this.isCall = isCall;
        this.strikePrice = strikePrice;
        this.riskFreeInterestValue = riskFreeInterestValue;
        this.upRate = upRate;
        this.downRate = downRate;
        this.exerciseTime = exerciseTime;
        this.stockPrice = stockPrice;
        this.bondPrice = bondPrice;
        this.probabilityOfUp = probabilityOfUp;
    }
    public Option(){}
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
