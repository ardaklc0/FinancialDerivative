package com.test.demo.dto.request.amortization;

public class UpdateExistingAmortizationRequest {
    private Long id;
    private Double couponRate;
    private Double yieldRate;
    private Double faceValue;
    private int paymentPeriod;
    private Double totalPaymentYear;
    private Double couponValue;
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
