package com.dealership;

import java.math.BigDecimal;

public class SalesContract extends Contract {
    private BigDecimal salesTax = BigDecimal.valueOf(0.05);  // Sales tax = 5%
    private BigDecimal recordingFee = BigDecimal.valueOf(100.00);
    private BigDecimal processingFee; // $295 for vehicles under $10000 and $495 for all others
    private boolean wantToFinance; // Whether customer want to finance (yes/no)
    private BigDecimal loanPercent; // 4.25% for 48 months if the price is $10,000 or more;
    // Otherwise they are at 5.25% for 24 months


    public SalesContract(String date, String name, String customerEmail, String vehicleSold, BigDecimal totalPrice, BigDecimal monthlyPayment, BigDecimal salesTax, BigDecimal recordingFee, BigDecimal processingFee, boolean wantToFinance, BigDecimal loanPercent) {
        super(date, name, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.salesTax = salesTax;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.wantToFinance = wantToFinance;
        this.loanPercent = loanPercent;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    public BigDecimal getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(BigDecimal recordingFee) {
        this.recordingFee = recordingFee;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isWantToFinance() {
        return wantToFinance;
    }

    public void setWantToFinance(boolean wantToFinance) {
        this.wantToFinance = wantToFinance;
    }

    public BigDecimal getLoanPercent() {
        return loanPercent;
    }

    public void setLoanPercent(BigDecimal loanPercent) {
        this.loanPercent = loanPercent;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        return null;
    }
}
