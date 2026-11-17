package com.dealership;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserInterface {
    private Dealership dealership;

    public UserInterface() {
    }

    private void init() {
        this.dealership = DealershipFileManager.getDealership();
    }

    public void display() throws InterruptedException {
        init();
        int selectedOption;
        printLineSlowly("Welcome to D & B car dealership!", 50);

        do {
            printLineSlowly("\n• loading...", 50);
            Thread.sleep(1000);
            System.out.println("""
                    \n---------------------------------------
                                 ⟨ MAIN MENU ⟩
                    ---------------------------------------""");
            Thread.sleep(1000);
            selectedOption = Console.promptForInt("""
                   [1]  Find vehicles within a price range
                   [2]  Find vehicles by make / model
                   [3]  Find vehicles by year range
                   [4]  Find vehicles by color
                   [5]  Find vehicles by mileage range
                   [6]  Find vehicles by type
                   [7]  View all vehicles
                   [8]  Add a vehicle
                   [9]  Remove a vehicle
                   [10] Quit
                   
                   › Select an option [1-10]: """);

            switch (selectedOption) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 10 -> System.out.println("\nHave a good day!");
                default -> System.out.println("\nInvalid command, please try again.");
            }
        } while (selectedOption != 10);
    }

    // helper method – displays list and can be called from all search
    // and get vehicles type of methods
    private void displayVehicles(ArrayList<Vehicle> selectedVehicles) {

        System.out.println("""
                \n╔═══════╦══════╦═══════════════╦════════════════╦══════════╦═══════════╦════════════╦═════════════╗
                ║ vin   ║ year ║ make          ║ model          ║ type     ║ color     ║   odometer ║       price ║
                ╠═══════╬══════╬═══════════════╬════════════════╬══════════╬═══════════╬════════════╬═════════════╣""");

        for (Vehicle v : selectedVehicles) {
            System.out.println(v);
        }
    }

    public void processGetByPriceRequest() {

        // user input
        BigDecimal min = Console.promptForBigDecimal("Enter minimum price range (press Enter↵ to skip): ");
        BigDecimal max = Console.promptForBigDecimal("Enter maximum price range (press Enter↵ to skip): ");

        ArrayList<Vehicle> result = dealership.getVehiclesByPrice(min, max);
        displayVehicles(result);
    }

    public void processGetByMakeModelRequest() {

        // user input
        String make;
        do {
            make = Console.promptForString("Enter make: ").trim().toLowerCase();
        } while (make.isEmpty());
        String model = Console.promptForString("Enter model (press Enter↵ to skip): ").trim().toLowerCase();

        ArrayList<Vehicle> result = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(result);
    }

    public void processGetByYearRequest() {

        // user input
        int min;
        int max;
        min = Console.promptForInt("Enter start of year range (press Enter↵ to skip): ");
        max = Console.promptForInt("Enter end of year range (press Enter↵ to skip): ");

        ArrayList<Vehicle> result = dealership.getVehiclesByYear(min, max);
        displayVehicles(result);
    }

    public void processGetByColorRequest() {

        // user input
        String color = Console.promptForString("Enter color: ").trim().toLowerCase();

        ArrayList<Vehicle> result = dealership.getVehiclesByColor(color);
        displayVehicles(result);
    }

    public void processGetByMileageRequest() {

        // user input
        int min;
        int max;
        min = Console.promptForInt("Enter minimum mileage (press Enter↵ to skip): ");
        max = Console.promptForInt("Enter maximum mileage (press Enter↵ to skip): ");

        ArrayList<Vehicle> result = dealership.getVehiclesByMileage(min, max);
        displayVehicles(result);
    }

    public void processGetByVehicleTypeRequest() {

        // user input
        String vehicleType = Console.promptForString("Enter type (car/SUV/coupe/truck/sedan/van): ").trim().toLowerCase();

        ArrayList<Vehicle> result = dealership.getVehiclesByType(vehicleType);
        displayVehicles(result);
    }

    public void processGetAllVehiclesRequest() {
        ArrayList<Vehicle> allVehicles = this.dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void processAddVehicleRequest() {

        // user input
        int vin = promptForVin();
        int year = promptForYear();
        String make = promptForMake();
        String model = promptForModel();
        String vehicleType = promptForVehicleType();
        String color = promptForColor();
        int odometer = promptForOdometer();
        BigDecimal price = promptForPrice();

        // create new vehicle
        Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

        // add it to inventory
        dealership.addVehicle(v);

        // save file
        DealershipFileManager.saveDealership(dealership);

        // success message
        System.out.println("\n✓ Vehicle is successfully added to inventory.");
    }

    public void processRemoveVehicleRequest() {
        // user input
        int vin = Console.promptForInt("\nEnter VIN of vehicle to remove: ");

        // remove from inventory
        dealership.removeVehicle(vin);

        // save file
        DealershipFileManager.saveDealership(dealership);

        // success message
        System.out.println("\n✓ Vehicle is successfully removed from inventory.");

    }

    public int promptForVin() {
        int vin = 0;
        vin = Console.promptForInt("\nEnter VIN (vehicle identification number): ");
        while (vin == 0) {
            vin = Console.promptForInt("Field cannot be empty. Enter VIN: ");
        }
        return vin;
    }

    public int promptForYear() {
        int year = 0;
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        year = Console.promptForInt("Enter model year: ");
        while (year < 1900 || year > currentYear) {
            year = Console.promptForInt("Model year should be between 1900 - " + currentYear + ". Enter model year: ");
        }
        return year;
    }

    public String promptForMake() {
        String make = "";
        make = Console.promptForString("Enter make: ");
        while (make.isEmpty()) {
            make = Console.promptForString("Field cannot be empty. Enter make: ");
        }
        return make;
    }

    public String promptForModel() {
        String model = "";
        model = Console.promptForString("Enter model: ");
        while (model.isEmpty()) {
            model = Console.promptForString("Field cannot be empty. Enter model: ");
        }
        return model;
    }

    public String promptForVehicleType() {
        String vehicleType = "";
        vehicleType = Console.promptForString("Enter vehicle type: ");
        while (vehicleType.isEmpty()) {
            vehicleType = Console.promptForString("Field cannot be empty. Enter vehicle type: ");
        }
        return vehicleType;
    }

    public String promptForColor() {
        String color = "";
        color = Console.promptForString("Enter color: ");
        while (color.isEmpty()) {
            color = Console.promptForString("Field cannot be empty. Enter color: ");
        }
        return color;
    }

    public int promptForOdometer() {
        int odometer = 0;
        odometer = Console.promptForInt("Enter mileage: ");
        while (odometer == 0) {
            odometer = Console.promptForInt("Field cannot be empty. Enter mileage: ");
        }
        return odometer;
    }

    public BigDecimal promptForPrice() {
        BigDecimal price = BigDecimal.ZERO;
        price = Console.promptForBigDecimal("Enter price: ");
        while (price.compareTo(BigDecimal.ZERO) == 0) {
            price = Console.promptForBigDecimal("Field cannot be empty. Enter price: ");
        }

        price = price.setScale(2, RoundingMode.HALF_UP);
        return price;
    }

    public static void printLineSlowly(String text, int delay) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            System.out.flush();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Something went wrong.");
                break;
            }
        }
        System.out.println();
    }
}
