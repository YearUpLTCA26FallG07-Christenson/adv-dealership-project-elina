package com.dealership;

import java.io.*;

public class ContractDataManager {
//    public static Dealership getDealership() {
//        String fileName = "inventory.csv";
//        Dealership dealership = null;
//
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
//
//            // split first line from file
//            String line = bufferedReader.readLine();
//            String[] parts = line.split(Pattern.quote("|"));
//            String name = parts[0];
//            String address = parts[1];
//            String phone = parts[2];
//
//            // create Dealership
//            dealership = new Dealership(name, address, phone);
//
//            // loop goes through every line in file
//            while ((line = bufferedReader.readLine()) != null) {
//                // convert each line to Vehicle object
//                Vehicle v = getVehicleFromLine(line);
//
//                // add vehicle to dealership
//                dealership.addVehicle(v);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("File was not found. Please try again.");
//        } catch (IOException e) {
//            System.out.println("△ Something went wrong. Please try again.");
//        }
//        return dealership;
//    }

    public static Contract getContractFromLine(String line) {

        // split each line into parts
        // SALE:
        //  CONTRACT_TYPE|DATE|CUSTOMER_NAME|CUSTOMER_EMAIL|VIN|YEAR|MAKE|MODEL|
        //  VEHICLE_TYPE|COLOR|ODOMETER|VEHICLE_PRICE|
        //  SALES_TAX|RECORDING_FEE|PROCESSING_FEE|TOTAL_PRICE|FINANCE_OPTION|MONTHLY_PAYMENT
        // LEASE:
        // CONTRACT_TYPE|DATE|CUSTOMER_NAME|CUSTOMER_EMAIL|VIN|YEAR|MAKE|MODEL|
        //  VEHICLE_TYPE|COLOR|ODOMETER|VEHICLE_PRICE|
        //  EXPECTED_ENDING_VALUE|LEASE_FEE|TOTAL_PRICE|MONTHLY_PAYMENT
//        try {
//            String[] parts = line.split(Pattern.quote("|"));
//
//            int vin = Integer.parseInt(parts[0]);
//            int year = Integer.parseInt(parts[1]);
//            String make = parts[2];
//            String model = parts[3];
//            String vehicleType = parts[4];
//            String color = parts[5];
//            int odometer = Integer.parseInt(parts[6]);
//            BigDecimal price = new BigDecimal(parts[7]);
//
//            return new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
//
//        } catch (Exception e) {
//            System.out.println("△ Something went wrong. Please try again.");
            return null;
    }

    // This method saves contract to contracts.csv
    public static void saveContract(Contract c) {
        try {
            String fileName = "contracts.csv";
            FileWriter writer = new FileWriter(fileName, true);

            // Selects appropriate string format for Sales and Lease contract
            if (c instanceof SalesContract) {
                System.out.println("SALE" + "|" + c.getDate() + "|" + c.getName() + "|" + c.getCustomerEmail()
                        + "|");
            }
            writer.write(c.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong. Please try again.");
        }
    }
}