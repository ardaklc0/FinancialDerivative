package com.test.demo.dto.request.riskneutral;

public class CreateNewRiskNeutralRequest {
    private boolean isCall;
    private Long strikePrice;
    private Double riskFreeInterestValue;
    private Double upRate;
    private Double downRate;
    private int exerciseTime;
    private Long stockPrice;
    private Double probabilityOfUp;
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
    public void setRiskFreeInterestValue(Double option_risk_free_interest_value) {
        this.riskFreeInterestValue = option_risk_free_interest_value;
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
    public void setExerciseTime(int option_exercise_time) {
        this.exerciseTime = option_exercise_time;
    }
    public Long getStockPrice() {
        return stockPrice;
    }
    public void setStockPrice(Long option_stock_price) {
        this.stockPrice = option_stock_price;
    }
    public Double getProbabilityOfUp() {
        return probabilityOfUp;
    }
    public void setProbabilityOfUp(Double option_probability_of_up) {
        this.probabilityOfUp = option_probability_of_up;
    }
}
