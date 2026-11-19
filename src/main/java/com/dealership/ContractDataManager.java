package com.dealership;

import java.io.*;

public class ContractDataManager {

    // This method saves contract to contracts.csv
    public static void saveContract(Contract c) throws IOException {
        FileWriter writer = null;
        try {
            String fileName = "contracts.csv";
            writer = new FileWriter(fileName, true);

            // Selects appropriate string method for Sales and Lease contract
            if (c instanceof SalesContract) {
                writer.write(((SalesContract) c).toEncodedString());
            } else if (c instanceof LeaseContract) {
                writer.write(((LeaseContract) c).toEncodedString());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong. Please try again.");
        } finally {
            assert writer != null;
            writer.close();
        }
    }
}