package org.example;

import java.util.Scanner;

public class Main {
    public static final double BaseRentalCost = 29.99;
    public static final double TollTagCost = 3.95;
    public static final double GPSCost = 2.95;
    public static final double RoadsideAssistanceCost = 3.95;
    public static final double UnderageSurgeRate = 1.3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String pickupDate = getPickupDate(scanner);
        int daysRented = getDaysRented(scanner);
        boolean tollTag = getBooleanUserInput(scanner, "Would you like a toll tag?");
        boolean GPS = getBooleanUserInput(scanner, "Would you like a GPS");
        boolean roadSideAssistance = getBooleanUserInput(scanner, "Would you like roadside assistance");
        int age = getUserAge(scanner);

        double basicRentalCost = calculateBasicRentalCost(daysRented);
        double optionsCost = calculateOptionsCost(daysRented, tollTag, GPS, roadSideAssistance);
        double surchargeCost = calculateSurchargeCost(basicRentalCost, age);
        double totalCost = calculateTotalCost(basicRentalCost, optionsCost, surchargeCost);

        displayCosts(pickupDate, basicRentalCost, optionsCost, surchargeCost, totalCost);
    }

    public static String getPickupDate(Scanner scanner) {
        System.out.print("Please enter the pickup date: ");
        return scanner.nextLine();
    }

    public static int getDaysRented(Scanner scanner) {
        System.out.print("Please enter the number of days you will like to rent for: ");
        return scanner.nextInt();
    }

    public static boolean getBooleanUserInput(Scanner scanner, String message) {
        System.out.print(message + "\n\tYes\n\tNo\nPlease chose one: ");
        return scanner.next().equalsIgnoreCase("yes") || scanner.next().equalsIgnoreCase("no");
    }

    public static int getUserAge(Scanner scanner) {
        System.out.print("Please enter your age: ");
        return scanner.nextInt();
    }

    public static double calculateBasicRentalCost(int daysRented) {
        return BaseRentalCost * daysRented;
    }

    public static double calculateOptionsCost(int daysRented, boolean tollTag, boolean GPS, boolean roadSideAssistance) {
        double optionsCost = 0.0;
        if (tollTag) optionsCost += TollTagCost * daysRented;
        if (GPS) optionsCost += GPSCost * daysRented;
        if (roadSideAssistance) optionsCost += RoadsideAssistanceCost * daysRented;
        return optionsCost;
    }

    public static double calculateSurchargeCost(double basicRentalCost, int age) {
        return age < 25 ? basicRentalCost * UnderageSurgeRate : 0.0;
    }

    public static double calculateTotalCost(double basicRentalCost, double optionsCost, double surchargeCost) {
        return basicRentalCost + optionsCost + surchargeCost;
    }

    public static void displayCosts(String pickupDate, double basicRentalCost, double optionsCost, double surchargeCost, double totalCost) {
        System.out.println("Pickup date: " + pickupDate);
        System.out.println("Basic rental cost: " + String.format("%.2f", basicRentalCost));
        System.out.println("Cost of additional options: " + String.format("%.2f", optionsCost));
        System.out.println("Age surcharge: " + String.format("%.2f", surchargeCost));
        System.out.println("Total Cost: " + String.format("%.2f", totalCost));
    }

}