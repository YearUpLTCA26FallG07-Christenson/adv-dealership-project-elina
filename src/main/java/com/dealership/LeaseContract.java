package com.dealership;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LeaseContract extends Contract {
    private BigDecimal expectedEndingValue; // 50% of the original price
    private BigDecimal leaseFee; // 7% of the original price
    private BigDecimal leasePercent; // 4.0% percent

    public LeaseContract(String date, String name, String customerEmail, Vehicle vehicleSold) {
        super(date, name, customerEmail, vehicleSold);
        this.expectedEndingValue = vehicleSold.getPrice() // 50% of the original price
                .divide(BigDecimal.valueOf(2.00))
                .setScale(2, RoundingMode.HALF_UP);
        this.leaseFee = vehicleSold.getPrice()
                .multiply(BigDecimal.valueOf(0.07)); // 7% of the original price
        this.leasePercent = BigDecimal.valueOf(0.04); // All leases are financed at 4.0% for 36 months
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

    @Override
    public BigDecimal getTotalPrice() {
        // Total price of a lease = Expected Ending Value + Lease Fee
        return getExpectedEndingValue()
                .add(getLeaseFee());
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        // Monthly payment = (lease percent * total price * years + total price) / 36
        return leasePercent
                .multiply(getVehicleSold().getPrice())
                .multiply(BigDecimal.valueOf(3)) // All leases are financed at 4.0% for 36 months = 3 years
        .add (getVehicleSold().getPrice())
                .divide(BigDecimal.valueOf(36));
    }

    // This method printing contract in .csv file format
    public String toEncodedString() {
        // LEASE:
        // CONTRACT_TYPE|DATE|CUSTOMER_NAME|CUSTOMER_EMAIL|VIN|YEAR|MAKE|MODEL|
        //  VEHICLE_TYPE|COLOR|ODOMETER|VEHICLE_PRICE|
        //  EXPECTED_ENDING_VALUE|LEASE_FEE|TOTAL_PRICE|MONTHLY_PAYMENT
        return "\n" + "LEASE" + "|" + getDate() + "|" + getName() + "|" + getCustomerEmail()
                + "|" + getVehicleSold().getVin() + "|" + getVehicleSold().getYear()
                + "|" + getVehicleSold().getMake() + "|" + getVehicleSold().getModel()
                + "|" + getVehicleSold().getVehicleType() + "|" + getVehicleSold().getColor()
                + "|" + getVehicleSold().getOdometer() + "|" + getVehicleSold().getPrice()
                + "|" + getExpectedEndingValue() + "|" + getLeaseFee() + "|" + getTotalPrice()
                + "|" + getMonthlyPayment();
    }
}
