package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderManager {
    private Order currentOrder;

    public void startNewOrder() {
        this.currentOrder = new Order();
    }

    public void addItemToOrder(Product product) {
        if (currentOrder != null) {
            currentOrder.addItem(product);
        }
    }

    public void finalizeOrder() {
        if (currentOrder != null) {
            Sandwich sandwich = null;
            Drink drink = null;
            Chips chips = null;

            // Extract items from the order
            for (Product item : currentOrder.getItems()) {
                if (item instanceof Sandwich) {
                    sandwich = (Sandwich) item;
                } else if (item instanceof Drink) {
                    drink = (Drink) item;
                } else if (item instanceof Chips) {
                    chips = (Chips) item;
                }
            }

            LocalDateTime now = LocalDateTime.now();
            String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
            String receiptFilePath = "src/main/resources/Receipts/" + formattedDate + ".txt";

            Receipt receipt = new Receipt(sandwich, drink, chips);
            receipt.generateReceiptToFile(receiptFilePath);

            System.out.println("Receipt is printing ..... ");
            System.out.println("Done. " +
                    "\n" +
                    "Thank your for ordering at DELI-cious");

            currentOrder = null;  // Reset the current order
            System.exit(0);  // Terminate the program after checkout
        } else {
            System.out.println("No order to finalize.");
        }
    }

}


