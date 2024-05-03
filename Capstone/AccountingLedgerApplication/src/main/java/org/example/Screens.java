package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Screens {
    private TransactionManager transactionManager;
    private ReportGenerator reportGenerator;
    private Scanner scanner;

    public Screens(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.reportGenerator = new ReportGenerator(transactionManager);
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add deposit");
            System.out.println("2. Make payment ");
            System.out.println("3. Display Ledger");
            System.out.println("4. Exit");
            String choice = getUserInput();

            switch (choice) {
                case "1":
                    addDeposit();
                    break;
                case "2":
                    addPayment();
                    break;
                case "3":
                    displayLedgerMenu();
                    break;
                case "4":
                    System.out.println("Exiting application...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    public void addDeposit() {
        try {
            System.out.print("Enter deposit description: ");
            String description = scanner.nextLine();
            System.out.print("Enter the source of the deposit (who it came from): ");
            String vendor = scanner.nextLine();
            System.out.print("Enter the amount of the deposit: ");
            double amount = Double.parseDouble(scanner.nextLine());

            // Ensure the amount is positive
            amount = Math.abs(amount);  // Use Math.abs to convert any negative amount to positive

            // Create timestamp details
            String date = java.time.LocalDate.now().toString();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String time = LocalTime.now().format(timeFormatter);

            // Create and add the deposit transaction
            TransactionManager.Transaction deposit = new TransactionManager.Transaction(date, time, description, vendor, amount);
            transactionManager.addTransaction(deposit);

            System.out.println("Deposit added successfully!");
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
    }

    public void addPayment() {
        try {
            System.out.print("Enter payment description: ");
            String description = scanner.nextLine();
            System.out.print("Enter the recipient of the payment (who it is to): ");
            String vendor = scanner.nextLine();
            System.out.print("Enter the amount of the payment: ");
            double amount = Double.parseDouble(scanner.nextLine());

            // Ensure the amount is negative for payments
            if (amount > 0) {
                amount = -amount;  // Negate the amount if it's positive
            }
            // Create timestamp details
            String date = java.time.LocalDate.now().toString();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String time = LocalTime.now().format(timeFormatter);

            // Create and add the payment transaction
            TransactionManager.Transaction payment = new TransactionManager.Transaction(date, time, description, vendor, amount);
            transactionManager.addTransaction(payment);

            System.out.println("Payment added successfully!");
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
    }

    public void displayLedgerMenu() {
        String choice;
        do {
            System.out.println("\nLedger Menu:");
            System.out.println("1. Show All Transactions");
            System.out.println("2. Show Deposits Only");
            System.out.println("3. Show Payments Only");
            System.out.println("4. Show Reports");
            System.out.println("5. Back to Main Menu");
            choice = getUserInput();

            switch (choice) {
                case "1":
                    displayAllEntries();
                    break;
                case "2":
                    displayDeposits();
                    break;
                case "3":
                    displayPayments();
                    break;
                case "4":
                    displayReportsMenu();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        while (!choice.equals("5"));
    }

    public void displayReportsMenu() {
        String choice;
        do {
            System.out.println("\nReports Menu:");
            System.out.println("1. Month-to-Date Report");
            System.out.println("2. Previous Month Report");
            System.out.println("3. Year-to-Date Report");
            System.out.println("4. Previous Year Report");
            System.out.println("5. Search by Vendor");
            System.out.println("6. Back to Ledger Menu");
            choice = getUserInput();

            switch (choice) {
                case "1":
                    reportGenerator.generateMonthToDateReport();
                    break;
                case "2":
                    reportGenerator.generatePreviousMonthReport();
                    break;
                case "3":
                    reportGenerator.generateYearToDateReport();
                    break;
                case "4":
                    reportGenerator.generatePreviousYearReport();
                    break;
                case "5":
                    System.out.println("Enter vendor name:");
                    String vendor = getUserInput();
                    reportGenerator.searchByVendor(vendor);
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        while (!choice.equals("6"));
    }

    public void displayAllEntries() {
        List<TransactionManager.Transaction> allTransactions = transactionManager.getTransactions();
        if (allTransactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (TransactionManager.Transaction transaction : allTransactions) {
                System.out.println(transaction.toString());
            }
        }
        System.out.println("---------------------------------------------");
        System.out.println("Displaying " + allTransactions.size() + " transactions.");
        System.out.println("---------------------------------------------");
    }

    public void displayDeposits() {
        List<TransactionManager.Transaction> deposits = transactionManager.filterDeposits();
        if (deposits.isEmpty()) {
            System.out.println("No deposits found.");
        } else {
            for (TransactionManager.Transaction deposit : deposits) {
                System.out.println(deposit);
            }
        }
    }

    public void displayPayments() {
        List<TransactionManager.Transaction> payments = transactionManager.filterPayments();
        if (payments.isEmpty()) {
            System.out.println("No payments found.");
        } else {
            for (TransactionManager.Transaction payment : payments) {
                System.out.println(payment);
            }
        }
    }

    private String getUserInput() {
        System.out.print("Enter your choice: ");
        return scanner.nextLine();
    }
}