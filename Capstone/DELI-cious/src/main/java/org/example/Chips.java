package org.example;

public abstract class Chips extends Product {
    private String type; // "Regular", "BBQ", "Salt & Vinegar"

    public Chips(double price, String description, String type) {
        super(description, price);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
