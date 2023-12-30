package com.test.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "amortization")
public class Amortization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "coupon_rate")
    private Double couponRate;
    @Column(name = "yield_rate")
    private Double yieldRate;
    @Column(name = "face_value")
    private Double faceValue;
    @Column(name = "payment_period")
    private int paymentPeriod;
    @Column(name = "total_payment_year")
    private Double totalPaymentYear;
    @Column(name = "coupon_value")
    private Double couponValue;

    public Amortization(Double couponRate, Double yieldRate, Double faceValue, int paymentPeriod, Double totalPaymentYear, Double couponValue){
        this.couponRate = couponRate;
        this.yieldRate = yieldRate;
        this.faceValue = faceValue;
        this.paymentPeriod = paymentPeriod;
        this.totalPaymentYear = totalPaymentYear;
        this.couponValue = couponValue;
    }
    public Amortization(){
    }
    public Long getId() {
        return id;
    }
    public void setId(Long amortization_id) {
        this.id = amortization_id;
    }
    public Double getCouponRate() {
        return couponRate;
    }
    public void setCouponRate(Double amortization_coupon_rate) {
        this.couponRate = amortization_coupon_rate;
    }
    public Double getYieldRate() {
        return yieldRate;
    }
    public void setYieldRate(Double amortization_yield_rate) {
        this.yieldRate = amortization_yield_rate;
    }
    public Double getFaceValue() {
        return faceValue;
    }
    public void setFaceValue(Double amortization_face_value) {
        this.faceValue = amortization_face_value;
    }
    public int getPaymentPeriod() {
        return paymentPeriod;
    }
    public void setPaymentPeriod(int amortization_payment_period) {
        this.paymentPeriod = amortization_payment_period;
    }
    public Double getTotalPaymentYear() {
        return totalPaymentYear;
    }
    public void setTotalPaymentYear(Double amortization_total_payment_year) {
        this.totalPaymentYear = amortization_total_payment_year;
    }
    public Double getCouponValue() {
        return couponValue;
    }
    public void setCouponValue(Double amortization_coupon_value) {
        this.couponValue = amortization_coupon_value;
    }
}
