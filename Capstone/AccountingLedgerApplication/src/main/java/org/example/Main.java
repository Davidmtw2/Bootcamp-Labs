package org.example;

public class Main {
    public static void main(String[] args) {
        // Creates a new instance of TransactionManager
        TransactionManager transactionManager = new TransactionManager("src/main/resources/transactions.csv");

        // Load transactions from CSV file
        transactionManager.readTransactions();

        // Instantiates the Screens class with the transaction manager
        Screens screens = new Screens(transactionManager);

        // Show loading animation
        screens.loadingSpinner();

        // Displays the main menu to the user
        screens.displayMainMenu();
    }
}