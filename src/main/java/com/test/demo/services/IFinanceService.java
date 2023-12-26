package com.test.demo.services;

public interface IFinanceService {
    public long calculateUpValue(long stockPrice, double upRate);
    public long calculateDownValue(long stockPrice, double downRate);
    public long calculateFutureBond(long bondPrice, double riskFreeInterestValue);
    public long calculateUpPortfolio(long stockPrice, long bondPrice, double upRate, double riskFreeInterestValue);
    public long calculateDownPortfolio(long stockPrice, long bondPrice, double downRate, double riskFreeInterestValue);
    public long calculateLayOff(long strikePrice, long stockPrice, double upRate);
}
