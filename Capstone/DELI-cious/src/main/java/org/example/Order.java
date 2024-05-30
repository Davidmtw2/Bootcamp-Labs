package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {
    // Assuming there is a list to hold items
    private List<Product> items = new ArrayList<>();

    public void addItem(Product product) {
        items.add(product);
    }

    public double calculateTotalCost() {
        return items.stream().mapToDouble(Product::getPrice).sum();
    }

    public String getOrderDetails() {
        StringBuilder details = new StringBuilder();
        for (Product item : items) {
            details.append(item.getDescription()).append(", $").append(item.getPrice()).append("\n");
        }
        return details.toString();
    }
    public List<Product> getItems() {
        return items;
    }

}
