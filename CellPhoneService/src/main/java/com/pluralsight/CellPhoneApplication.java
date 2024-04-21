package com.pluralsight;

import java.util.Scanner;

public class CellPhoneApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creating the first call phone:");
        CellPhone cellPhone1 = createCellPhone(scanner);
        System.out.println("---------------------------------------------------------------");
        System.out.println("Creating the second call phone:");
        CellPhone cellPhone2 = new CellPhone(2598741, "Android", "T-Mobile", "855-555-2222", "Dana Wyatt");



        System.out.println("\n---------------------------------------------------------------");
        display(cellPhone1);
        display(cellPhone2);

        System.out.println("\n---------------------------------------------------------------");
        cellPhone1.dial(cellPhone2);
        cellPhone2.dial(cellPhone1);
        scanner.close();
    }
    private static CellPhone createCellPhone(Scanner scanner) {
        CellPhone phone = new CellPhone();
        System.out.print("What is the serial number?: ");
        int serialNumber = scanner.nextInt();
        scanner.nextLine();
        phone.setSerialNumber(serialNumber);

        System.out.print("What model is the phone?: ");
        String phoneModel = scanner.next();
        scanner.nextLine();
        phone.setModel(phoneModel);

        System.out.print("What is the carrier?: ");
        String carrier = scanner.next();
        scanner.nextLine();
        phone.setCarrier(carrier);

        System.out.print("What is the phone number?: ");
        String phoneNumber = scanner.next();
        scanner.nextLine();
        phone.setPhoneNumber(phoneNumber);

        System.out.print("What is the owner of the phone?: ");
        String owner = scanner.next();
        scanner.nextLine();
        phone.setOwner(owner);

        return phone;
    }
    private static void display(CellPhone cellPhone) {
        System.out.println("\nCell phone details:");
        System.out.println("Serial Number: " + cellPhone.getSerialNumber());
        System.out.println("Model: " + cellPhone.getModel());
        System.out.println("Carrier: " + cellPhone.getCarrier());
        System.out.println("Phone number: " + cellPhone.getPhoneNumber());
        System.out.println("Owner: " + cellPhone.getOwner());
    }
}
