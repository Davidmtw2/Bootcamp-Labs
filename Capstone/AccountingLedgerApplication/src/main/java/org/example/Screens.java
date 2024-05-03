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
            simulateTyping("\033[1;36m==================================================\033[0m\n", 3);
            simulateTyping("\033[1;33m                Accounting Ledger App\033[0m\n", 3);
            simulateTyping("\033[1;36m==================================================\033[0m\n\n", 3);
            simulateTyping("\033[1;34m---------------- MAIN MENU ----------------\033[0m\n", 3);
            simulateTyping("                1. Add deposit\n", 3);
            simulateTyping("                2. Make payment \n", 3);
            simulateTyping("                3. Display Ledger\n", 3);
            simulateTyping("                4. Exit\n", 3);
            simulateTyping("                Enter your choice: ", 3);
            String choice = scanner.nextLine();
            System.out.println();  // Ensure there's a break after the choice is entered.

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
                    simulateTyping("              \033[1;34mExiting application...\033[0m",3);
                    return;
                default:
                    simulateTyping("Invalid option, please try again.\n",3);
            }
        }
    }

    public void addDeposit() {
        try {
            simulateTyping("\033[1;32m------------- Add Deposit ----------------\033[0m\n",3);
            simulateTyping("Enter deposit description: ",3);
            String description = scanner.nextLine();
            simulateTyping("Enter the source of the deposit (who it came from): ",3);
            String vendor = scanner.nextLine();
            simulateTyping("Enter the amount of the deposit: ",3);
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

            simulateTyping("\033[1;32mDeposit added successfully!\033[0m\n",3);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
    }

    public void addPayment() {
        try {
            simulateTyping("\033[1;31m------------- Make Payment ----------------\033[0m\n",3);
            simulateTyping("Enter payment description: ",3);
            String description = scanner.nextLine();
            simulateTyping("Enter the recipient of the payment (who it is to): ",3);
            String vendor = scanner.nextLine();
            simulateTyping("Enter the amount of the payment: ",3);
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

            simulateTyping("\033[1;31mPayment added successfully!\033[0m\n",3);

        }
        catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
    }

    public void displayLedgerMenu() {
        String choice;
        do {
            simulateTyping("\033[1;35m-------------- LEDGER MENU ---------------\033[0m\n",3);
            simulateTyping("              1. Show All Transactions\n",3);
            simulateTyping("              2. Show Deposits Only\n",3);
            simulateTyping("              3. Show Payments Only\n",3);
            simulateTyping("              4. Show Reports\n",3);
            simulateTyping("              5. Back to Main Menu\n",3);
            choice = getUserInput();

            switch (choice) {
                case "1":
                    simulateTyping("\033[1;33mDate        | Time    | Description                    | Vendor                    | Amount\033[0m\n",3);
                    displayAllEntries();
                    break;
                case "2":
                    simulateTyping("\033[1;33mDate        | Time    | Description                    | Vendor                    | Amount\033[0m\n",3);
                    displayDeposits();
                    break;
                case "3":
                    simulateTyping("\033[1;33mDate        | Time    | Description                    | Vendor                    | Amount\033[0m\n",3);
                    displayPayments();
                    break;
                case "4":

                    displayReportsMenu();
                    break;
                case "5":
                    return;
                default:
                    simulateTyping("\033[1;31mInvalid option, please try again.\033[0m\n",3);
            }
        }
        while (!choice.equals("5"));
    }

    public void displayReportsMenu() {
        String choice;
        do {
            simulateTyping("\033[1;35m-------------- REPORTS MENU ---------------\033[0m\n",3);
            simulateTyping("              1. Month-to-Date Report\n",3);
            simulateTyping("              2. Previous Month Report\n",3);
            simulateTyping("              3. Year-to-Date Report\n",3);
            simulateTyping("              4. Previous Year Report\n",3);
            simulateTyping("              5. Search by Vendor\n",3);
            simulateTyping("              6. Back to Ledger Menu\n",3);
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
                    simulateTyping("\033[1;35m-------------- Vendors Name ---------------\033[0m\n",3);
                    String vendor = getUserInput();
                    reportGenerator.searchByVendor(vendor);
                    break;
                case "6":
                    return;
                default:
                    simulateTyping("\033[1;31mInvalid option, please try again.\033[0m\n",3);
            }
        }
        while (!choice.equals("6"));
    }

    public void displayAllEntries() {
        List<TransactionManager.Transaction> allTransactions = transactionManager.getTransactions();
        if (allTransactions.isEmpty()) {
            simulateTyping("No transactions found.\n",1);
        } else {
            for (TransactionManager.Transaction transaction : allTransactions) {
                simulateTyping(transaction.toString()+"\n",1);
            }
        }
        simulateTyping("---------------------------------------------\n",1);
        simulateTyping("Displaying " + allTransactions.size() + " transactions.\n",1);
        simulateTyping("---------------------------------------------\n",1);
    }

    public void displayDeposits() {
        List<TransactionManager.Transaction> deposits = transactionManager.filterDeposits();
        if (deposits.isEmpty()) {
            simulateTyping("No deposits found.",1);
        } else {
            for (TransactionManager.Transaction deposit : deposits) {
                simulateTyping(deposit+"\n",1);
            }
        }

    }

    public void displayPayments() {
        List<TransactionManager.Transaction> payments = transactionManager.filterPayments();
        if (payments.isEmpty()) {
            simulateTyping("No payments found.",1);
        } else {
            for (TransactionManager.Transaction payment : payments) {
                simulateTyping(payment+"\n",1);
            }
        }
    }

    private String getUserInput() {
        System.out.print("                Enter your choice: ");
        return scanner.nextLine();
    }

    private void simulateTyping(String message, int delay) {
        for (char ch : message.toCharArray()) {
            System.out.print(ch);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void loadingSpinner() {
        System.out.print("Loading one moment please");  // Initial message before the spinner
        String[] spinner = new String[] { "|", "/", "-", "\\" };
        for (int i = 0; i < 20; i++) {  // Number of cycles the spinner will make
            System.out.print("\r" + spinner[i % spinner.length]);
            try {
                Thread.sleep(150);  // Pause to make the spinner visible (200 milliseconds)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Handle thread interruption
                System.out.println("Interrupted");
            }
        }
        System.out.print("\rREADY TO GO!          \n");  // Clear the spinner after loading
    }
}
