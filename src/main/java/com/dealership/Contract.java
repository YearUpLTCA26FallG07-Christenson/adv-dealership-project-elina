package com.dealership;

import java.math.BigDecimal;

public abstract class Contract {
    private String date;
    private String name;
    private String customerEmail;
    private String vehicleSold;
    private BigDecimal totalPrice;
    private BigDecimal monthlyPayment;

    public Contract(String date, String name, String customerEmail, String vehicleSold, BigDecimal totalPrice, BigDecimal monthlyPayment) {
        this.date = date;
        this.name = name;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
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

    public String getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(String vehicleSold) {
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

    public String toEncodedString() {
        return "Contract{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", vehicleSold='" + vehicleSold + '\'' +
                ", totalPrice=" + totalPrice +
                ", monthlyPayment=" + monthlyPayment +
                '}';
    }
}
