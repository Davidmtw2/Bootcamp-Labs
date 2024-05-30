package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {
    private Sandwich sandwich;
    private Drink drink;
    private Chips chips;

    public Receipt(Sandwich sandwich, Drink drink, Chips chips) {
        this.sandwich = sandwich;
        this.drink = drink;
        this.chips = chips;
    }

    public void generateReceiptToFile(String filePath) {
        StringBuilder receipt = new StringBuilder("Order Receipt\n");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        receipt.append("Date: ").append(sdf.format(new Date())).append("\n");

        // Sandwich details
        if (sandwich != null) {
            receipt.append("Sandwich total: $").append(String.format("%.2f", sandwich.getPrice())).append("\n");
            receipt.append("    - ").append(sandwich.getSize()).append("\n");
            receipt.append("    - ").append(sandwich.getBreadType()).append(" Bread\n");
            receipt.append("    - Meat: ").append(sandwich.getMeat()).append("\n");
            if (sandwich.isExtraMeat()) {
                receipt.append("    - Extra meat\n");
            }
            receipt.append("    - Cheese: ").append(sandwich.getCheese()).append("\n");
            if (sandwich.isExtraCheese()) {
                receipt.append("    - Extra cheese\n");
            }
            sandwich.getRegularToppings().forEach(t -> receipt.append("    - ").append(t.getName()).append("\n"));
            sandwich.getRegularSauces().forEach(t -> receipt.append("    - ").append(t.getName()).append("\n"));
        }

        // Drink details
        if (drink != null) {
            receipt.append("Drinks: ").append(drink.getFlavor()).append(", ").append(drink.getSize()).append(": $").append(String.format("%.2f", drink.getPrice())).append("\n");
        }

        // Chips details
        if (chips != null) {
            receipt.append("Chips: ").append(chips.getType()).append(": $").append(String.format("%.2f", chips.getPrice())).append("\n");
        }

        // Calculate total
        double total = (sandwich != null ? sandwich.getPrice() : 0) +
                (drink != null ? drink.getPrice() : 0) +
                (chips != null ? chips.getPrice() : 0);
        receipt.append("Total: $").append(String.format("%.2f", total));

        // Write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(receipt.toString());
        } catch (IOException e) {
            System.out.println("Error writing receipt to file: " + e.getMessage());
        }
    }
}

