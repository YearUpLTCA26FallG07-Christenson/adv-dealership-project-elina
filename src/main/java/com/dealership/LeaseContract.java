package com.dealership;

import java.math.BigDecimal;

public class LeaseContract extends Contract {
    private BigDecimal expectedEndingValue; // 50% of the original price
    private BigDecimal leaseFee; // 7% of the original price
    private BigDecimal leasePercent; // 4.0% percent
    private int duration = 36; // 36 months

    public LeaseContract(String date, String name, String customerEmail, String vehicleSold, BigDecimal totalPrice, BigDecimal monthlyPayment, BigDecimal expectedEndingValue, BigDecimal leaseFee, BigDecimal leasePercent, int duration) {
        super(date, name, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
        this.leasePercent = leasePercent;
        this.duration = duration;
    }

    public BigDecimal getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(BigDecimal expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public BigDecimal getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(BigDecimal leaseFee) {
        this.leaseFee = leaseFee;
    }

    public BigDecimal getLeasePercent() {
        return leasePercent;
    }

    public void setLeasePercent(BigDecimal leasePercent) {
        this.leasePercent = leasePercent;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public BigDecimal getTotalPrice() {
        // Total price of a lease = Expected Ending Value + Lease Fee.
        return null;
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        // Monthly payment based on
        //• All leases are financed at 4.0% for 36 months
        return null;
    }
}
