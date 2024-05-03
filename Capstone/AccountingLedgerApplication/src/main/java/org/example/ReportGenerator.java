package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ReportGenerator {
    private TransactionManager transactionManager;

    // Constructor for ReportGenerator
    public ReportGenerator(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    // Method to generate a report for transactions from the beginning of the current month to the current date
    public void generateMonthToDateReport() {
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1); // Get the first day of the current month
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Date format for parsing
        // Filter transactions from the start of the month till today, and collect them into a list
        List<TransactionManager.Transaction> transactions = transactionManager.getTransactions()
                .stream()
                .filter(t -> LocalDate.parse(t.getDate(), formatter).isAfter(startOfMonth.minusDays(1)))
                .collect(Collectors.toList());
        // Display the transactions with a specific title
        simulateTyping("              Month-to-Date Report\n", 1);
        simulateTyping("\033[1;33mDate        | Time    | Description                    | Vendor                    | Amount\033[0m\n", 1);

        for (TransactionManager.Transaction transaction : transactions) {
            simulateTyping(transaction.toString() + "\n", 1); // Ensure transaction.toString() formats it correctly
        }
    }

    // Method to generate a report for transactions from the previous month
    public void generatePreviousMonthReport() {
        LocalDate startOfPreviousMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1); // First day of previous month
        LocalDate endOfPreviousMonth = startOfPreviousMonth.plusMonths(1).minusDays(1); // Last day of previous month
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Date format for parsing
        // Filter transactions from the previous month and collect them into a list
        List<TransactionManager.Transaction> transactions = transactionManager.getTransactions()
                .stream()
                .filter(t -> LocalDate.parse(t.getDate(), formatter).compareTo(startOfPreviousMonth) >= 0 &&
                        LocalDate.parse(t.getDate(), formatter).compareTo(endOfPreviousMonth) <= 0)
                .collect(Collectors.toList());
        // Display the transactions with a specific title
        simulateTyping("              Previous Month Report\n", 1);
        simulateTyping("\033[1;33mDate        | Time    | Description                    | Vendor                    | Amount\033[0m\n", 1);

        for (TransactionManager.Transaction transaction : transactions) {
            simulateTyping(transaction.toString() + "\n", 1); // Ensure transaction.toString() formats it correctly
        }
    }

    // Method to generate a report for transactions from the beginning of the year to the current date
    public void generateYearToDateReport() {
        LocalDate startOfYear = LocalDate.now().withDayOfYear(1); // First day of the year
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Date format for parsing
        // Filter transactions from the start of the year till today, and collect them into a list
        List<TransactionManager.Transaction> transactions = transactionManager.getTransactions()
                .stream()
                .filter(t -> LocalDate.parse(t.getDate(), formatter).isAfter(startOfYear.minusDays(1)))
                .collect(Collectors.toList());
        // Display the transactions with a specific title
        simulateTyping("              Year-to-Date Report\n", 1);
        simulateTyping("\033[1;33mDate        | Time    | Description                    | Vendor                    | Amount\033[0m\n", 1);

        for (TransactionManager.Transaction transaction : transactions) {
            simulateTyping(transaction.toString() + "\n", 1); // Ensure transaction.toString() formats it correctly
        }
    }

    // Method to generate a report for transactions from the previous year
    public void generatePreviousYearReport() {
        LocalDate startOfLastYear = LocalDate.now().minusYears(1).withDayOfYear(1); // First day of previous year
        LocalDate endOfLastYear = startOfLastYear.plusYears(1).minusDays(1); // Last day of previous year
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Date format for parsing
        // Filter transactions from the previous year and collect them into a list
        List<TransactionManager.Transaction> transactions = transactionManager.getTransactions()
                .stream()
                .filter(t -> LocalDate.parse(t.getDate(), formatter).compareTo(startOfLastYear) >= 0 &&
                        LocalDate.parse(t.getDate(), formatter).compareTo(endOfLastYear) <= 0)
                .collect(Collectors.toList());
        // Display the transactions with a specific title
        simulateTyping("              Previous Year Report\n", 1);
        simulateTyping("\033[1;33mDate        | Time    | Description                    | Vendor                    | Amount\033[0m\n", 1);

        for (TransactionManager.Transaction transaction : transactions) {
            simulateTyping(transaction.toString() + "\n", 1); // Assuming transaction.toString() formats the transaction properly
        }
    }

    // Method to search for transactions by vendor
    public void searchByVendor(String vendor) {
        // Filter transactions by vendor and collect them into a list
        List<TransactionManager.Transaction> transactions = transactionManager.getTransactions()
                .stream()
                .filter(t -> t.getVendor().equalsIgnoreCase(vendor))
                .collect(Collectors.toList());
        // Display the transactions with a specific title
        simulateTyping("              Search Results for Vendor: " + vendor + "\n", 1);
        simulateTyping("\033[1;33mDate        | Time    | Description                    | Vendor                    | Amount\033[0m\n", 1);

        for (TransactionManager.Transaction transaction : transactions) {
            simulateTyping(transaction.toString() + "\n", 1); // Assuming transaction.toString() formats it correctly
        }
    }

    // Private helper method to display transactions
    private void displayTransactions(List<TransactionManager.Transaction> transactions, String reportTitle) {
        System.out.println("\n" + reportTitle); // Print the report title
        if (transactions.isEmpty()) {
            System.out.println("No transactions found."); // If no transactions found, print a message
        } else {
            transactions.forEach(System.out::println); // Print each transaction
        }
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
}
