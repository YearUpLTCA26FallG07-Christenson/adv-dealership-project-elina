package com.dealership;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.math.BigDecimal;

public class Program {
    public static void main(String[] args) throws IOException, InterruptedException {
//        UserInterface ui = new UserInterface();
//        ui.display();
        Vehicle vehicle = new Vehicle(2424, 2025, "Ford", "Explorer",
                "sedan", "black", 1000, BigDecimal.valueOf(1000));
        SalesContract contract = new SalesContract("2025", "Jess", "email", vehicle,
                BigDecimal.valueOf(100), true, BigDecimal.valueOf(0.04)) {
            @Override
            public BigDecimal getTotalPrice() {
                return null;
            }

            @Override
            public BigDecimal getMonthlyPayment() {
                return null;
            }
        };
        System.out.println(contract.toEncodedString());
    }
}