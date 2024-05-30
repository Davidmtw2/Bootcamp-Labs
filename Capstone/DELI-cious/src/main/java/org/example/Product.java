package org.example;

public abstract class Product {
    protected String name;
    protected double price;

    public Product() {
        // Default constructor
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Abstract method to be implemented by all subclasses to describe the product details.
    public abstract String getDescription();
}

