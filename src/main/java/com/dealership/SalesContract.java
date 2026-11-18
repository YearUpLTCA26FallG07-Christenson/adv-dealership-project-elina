package com.dealership;

import java.math.BigDecimal;

public class SalesContract extends Contract {
    private BigDecimal salesTax;
    private BigDecimal recordingFee;
    private BigDecimal processingFee; // $295 for vehicles under $10000 and $495 for all others
    private boolean isFinanced; // Whether customer want to finance (yes/no)
    private BigDecimal loanPercent; // 4.25% for 48 months if the price is $10,000 or more;
    // Otherwise they are at 5.25% for 24 months


    public SalesContract(String date, String name, String customerEmail, Vehicle vehicleSold, BigDecimal processingFee, boolean isFinanced, BigDecimal loanPercent) {
        super(date, name, customerEmail, vehicleSold);
        this.salesTax = BigDecimal.valueOf(5); // Sales tax = 5%
        this.recordingFee = BigDecimal.valueOf(100.00); // Recording fee = $100;
        this.processingFee = processingFee;
        this.isFinanced = isFinanced;
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

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setIsFinanced(boolean isFinanced) {
        this.isFinanced = isFinanced;
    }

    public BigDecimal getLoanPercent() {
        return loanPercent;
    }

    public void setLoanPercent(BigDecimal loanPercent) {
        this.loanPercent = loanPercent;
    }

    @Override
    public BigDecimal getTotalPrice() {
        // Vehicle price + recording fee + processing fee
//        BigDecimal totalPrice = getTotalPrice()
        return null;
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        return null;
    }

    // This method printing contract in .csv file format
    public String toEncodedString() {
        //  CONTRACT_TYPE|DATE|CUSTOMER_NAME|CUSTOMER_EMAIL|VIN|YEAR|MAKE|MODEL|
        //  VEHICLE_TYPE|COLOR|ODOMETER|VEHICLE_PRICE|
        //  SALES_TAX|RECORDING_FEE|PROCESSING_FEE|TOTAL_PRICE|FINANCE_OPTION|MONTHLY_PAYMENT
        return "\n" + "SALE" + "|" + getDate() + "|" + getName() + "|" + getCustomerEmail()
                + "|" + getVehicleSold().getVin() + "|" + getVehicleSold().getYear()
                + "|" + getVehicleSold().getMake() + "|" + getVehicleSold().getModel()
                + "|" + getVehicleSold().getVehicleType() + "|" + getVehicleSold().getColor()
                + "|" + getVehicleSold().getOdometer() + "|" + getVehicleSold().getPrice()
                + "|" + getSalesTax() + "|" + getRecordingFee() + "|" + getProcessingFee()
                + "|" + getTotalPrice() + "|" + isFinanced() + "|" + getMonthlyPayment();
    }
}
