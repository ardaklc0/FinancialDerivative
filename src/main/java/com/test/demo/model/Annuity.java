package com.test.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "annuity")
public class Annuity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "rate_of_interest")
    private Double rateOfInterest;
    @Column(name = "payment_period")
    private int paymentPeriod;
    @Column(name = "total_payment_year")
    private Double totalPaymentYear;
    @Column(name = "borrowed_loan", nullable = true)
    private Double borrowedLoan;
    @Column(name = "wanna_save", nullable = true)
    private Double wannaSave;

    public Annuity(Double rateOfInterest, int paymentPeriod, Double totalPaymentYear, Double borrowedLoan, Double wannaSave){
        this.rateOfInterest = rateOfInterest;
        this.paymentPeriod = paymentPeriod;
        this.totalPaymentYear = totalPaymentYear;
        this.borrowedLoan = borrowedLoan;
        this.wannaSave = wannaSave;
    }
    public Annuity(Double borrowedLoan, Double wannaSave){
        this.borrowedLoan = borrowedLoan;
        this.wannaSave = wannaSave;
    }
    public Annuity(){
    }
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
