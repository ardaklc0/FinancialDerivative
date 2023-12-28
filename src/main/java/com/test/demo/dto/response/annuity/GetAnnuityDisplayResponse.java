package com.test.demo.dto.response.annuity;

public class GetAnnuityDisplayResponse {
    private Long id;
    private Double rateOfInterest;
    private int paymentPeriod;
    private Double totalPaymentYear;
    private Double borrowedLoan;
    private Double wannaSave;

    public Long getId() {
        return id;
    }
    public void setId(Long annuity_id) {
        this.id = annuity_id;
    }
    public Double getRateOfInterest() {
        return rateOfInterest;
    }
    public void setRateOfInterest(Double annuity_rate_of_interest) {
        this.rateOfInterest = annuity_rate_of_interest;
    }
    public int getPaymentPeriod() {
        return paymentPeriod;
    }
    public void setPaymentPeriod(int annuity_payment_period) {
        this.paymentPeriod = annuity_payment_period;
    }
    public Double getTotalPaymentYear() {
        return totalPaymentYear;
    }
    public void setTotalPaymentYear(Double annuity_total_payment_year) {
        this.totalPaymentYear = annuity_total_payment_year;
    }
    public Double getBorrowedLoan() {
        return borrowedLoan;
    }
    public void setBorrowedLoan(Double annuity_borrowed_loan) {
        this.borrowedLoan = annuity_borrowed_loan;
    }
    public Double getWannaSave() {
        return wannaSave;
    }
    public void setWannaSave(Double annuity_wanna_save) {
        this.wannaSave = annuity_wanna_save;
    }
}
