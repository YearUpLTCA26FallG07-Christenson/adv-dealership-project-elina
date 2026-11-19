package com.dealership;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalesContract extends Contract {
    private BigDecimal salesTax;
    private BigDecimal recordingFee;
    private BigDecimal processingFee;
    private boolean isFinanced; // Whether customer want to finance (yes/no)
    private BigDecimal loanPercent; // 4.25% for 48 months if the price is $10,000 or more;
    // Otherwise they are at 5.25% for 24 months

    public SalesContract(String date, String name, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(date, name, customerEmail, vehicleSold);
        this.salesTax = BigDecimal.valueOf(5); // Sales tax = 5%
        this.recordingFee = BigDecimal.valueOf(100.00).setScale(2, RoundingMode.HALF_UP); // Recording fee = $100;
        if (vehicleSold.getPrice().compareTo(BigDecimal.valueOf(10000.00)) < 0) {
            processingFee = BigDecimal.valueOf(295.00).setScale(2, RoundingMode.HALF_UP); // $295 for vehicles under $10000
        } else {
            processingFee = BigDecimal.valueOf(495.00).setScale(2, RoundingMode.HALF_UP); // $495 for all others
        }
        this.isFinanced = isFinanced;
        if (vehicleSold.getPrice().compareTo(BigDecimal.valueOf(10000.00)) < 0) { // if the price under $10,000 – 5.25%
            loanPercent = BigDecimal.valueOf(5.25);
        } else { // if the price over $10,000 – 5.25%
            loanPercent = BigDecimal.valueOf(4.25);
        }
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

    // Calculate total price with flat interest over certain amount of months
    @Override
    public BigDecimal getTotalPrice() {
        // Total price = vehicle price + recording fee + processing fee + sales tax +
        // (vehicle price * loanPercent * years)
        BigDecimal vehiclePrice = getVehicleSold().getPrice(); // Base vehicle price
        BigDecimal term = null;
        if (vehiclePrice.compareTo(BigDecimal.valueOf(10000.00)) < 0) {
            term = BigDecimal.valueOf(2.00); // if price under $10,000: financing for 24 months = 2 years
        } else {
            term = BigDecimal.valueOf(4.00); // if price over or equal $10,000: financing for 48 months = 4 years
        }

        BigDecimal tax = vehiclePrice
                .multiply(salesTax)
                .divide(BigDecimal.valueOf(100.00));
        BigDecimal subtotal = vehiclePrice
                .add(recordingFee)
                .add(processingFee)
                .add(tax);
        BigDecimal interest = vehiclePrice
                .multiply(loanPercent)
                .multiply(term)
                .divide(BigDecimal.valueOf(100.00));
        return subtotal
                .add(interest)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        BigDecimal vehiclePrice = getVehicleSold().getPrice(); // Base vehicle price
        if (isFinanced == false) { // If customer selected no financing there are no monthly payments
            return BigDecimal.ZERO;
        } else {
            if (vehiclePrice.compareTo(BigDecimal.valueOf(10000.00)) < 0) { // If price under $10,000
                return getTotalPrice().divide(BigDecimal.valueOf(24.00)); // Divide total price by 24 months
            } else { // If price over $10,000
                return getTotalPrice().divide(BigDecimal.valueOf(48.00)).setScale(2, RoundingMode.HALF_UP); // Divide total price by 48 months
            }
        }
    }

    // Display contract info to customer
    public String toString() {
        return "\nSales contract" +
                "\nVehicle: " + getVehicleSold().getYear()
                + "|" + getVehicleSold().getMake() + "|" + getVehicleSold().getModel()
                + "|" + getVehicleSold().getVehicleType() + "|" + getVehicleSold().getColor()
                + "|" + getVehicleSold().getOdometer() + " miles " +
                "\nBase price: " + getVehicleSold().getPrice() +
                "\nSales tax: " + getSalesTax() + "%" +
                "\nRecording fee: " + getRecordingFee() +
                "\nProcessing fee: " + getProcessingFee() +
                "\nTotal price: " + getTotalPrice();
    }

    // This method printing contract in .csv file format
    public String toEncodedString() {
        //  CONTRACT_TYPE|DATE|CUSTOMER_NAME|CUSTOMER_EMAIL|VIN|YEAR|MAKE|MODEL|
        //  VEHICLE_TYPE|COLOR|ODOMETER|VEHICLE_PRICE|
        //  SALES_TAX|RECORDING_FEE|PROCESSING_FEE|TOTAL_PRICE|FINANCE_OPTION|MONTHLY_PAYMENT
        String financed;
        if(isFinanced) {
            financed = "YES";
        } else {
            financed = "NO";
        }

        BigDecimal vehiclePrice = getVehicleSold().getPrice(); // Base vehicle price

        BigDecimal tax = vehiclePrice
                .multiply(salesTax)
                .divide(BigDecimal.valueOf(100.00));

        return "\n" + "SALE" + "|" + getDate() + "|" + getName() + "|" + getCustomerEmail()
                + "|" + getVehicleSold().getVin() + "|" + getVehicleSold().getYear()
                + "|" + getVehicleSold().getMake() + "|" + getVehicleSold().getModel()
                + "|" + getVehicleSold().getVehicleType() + "|" + getVehicleSold().getColor()
                + "|" + getVehicleSold().getOdometer() + "|" + getVehicleSold().getPrice()
                + "|" + tax + "|" + getRecordingFee() + "|" + getProcessingFee()
                + "|" + getTotalPrice() + "|" + financed + "|" + getMonthlyPayment();
    }
}
