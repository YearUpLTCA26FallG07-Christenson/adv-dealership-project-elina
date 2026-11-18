package com.dealership;

import java.math.BigDecimal;
import java.util.ArrayList;

public abstract class Contract {
    private String date;
    private String name;
    private String customerEmail;
    private Vehicle vehicleSold;
    private BigDecimal totalPrice;
    private BigDecimal monthlyPayment;

    public Contract(String date, String name, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.name = name;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
        this.totalPrice = getTotalPrice();
        this.monthlyPayment = getMonthlyPayment();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        //VIN|YEAR|MAKE|MODEL|VEHICLE_TYPE|COLOR|ODOMETER|VEHICLE_PRICE;
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    // Child classes must override this method
    public abstract BigDecimal getTotalPrice();

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Child classes must override this method
    public abstract BigDecimal getMonthlyPayment();

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

}
