package org.example;

import java.util.ArrayList;
import java.util.List;

public class Sandwich extends Product {
    private String size;
    private String breadType;
    private String meat;
    private boolean extraMeat;
    private String cheese;
    private boolean extraCheese;
    private List<Topping> regularToppings;
    private List<Topping> regularSauces;
//
    public Sandwich(String size) {
        super();
        this.size = size;
        regularToppings = new ArrayList<>();
        regularSauces = new ArrayList<>();
        initializeBasePrice();
    }
    private void initializeBasePrice() {
        //Base price for the bread based on size
        switch(size) {
            case "4":
                this.price = 5.50;
                break;
            case "8":
                this.price = 7.00;
                break;
            case "12":
                this.price = 8.50;
                break;
        }
    }
    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public void setMeat(String meat) {
        if(!meat.isEmpty()) {
            this.meat = meat;
            //Add base meat price based on size
            switch (size) {
                case "4":
                    this.price += 1.00; // Price for 4" meat
                    break;
                case "8":
                    this.price += 2.00; // Price for 8" meat
                    break;
                case "12":
                    this.price += 3.00; // Price for 12" meat
                    break;
            }
        }
    }

    public void setExtraMeat(boolean extraMeat) {
        this.extraMeat = extraMeat;
        if (extraMeat) {
            // Add additional meat cost based on size
            switch(size) {
                case "4":
                    this.price += 0.50; // Additional price for 4" meat
                    break;
                case "8":
                    this.price += 1.00; // Additional price for 8" meat
                    break;
                case "12":
                    this.price += 1.50; // Additional price for 12" meat
                    break;
            }
        }
    }

    public void setCheese(String cheese) {
        if(!cheese.isEmpty()) {
            this.cheese = cheese;
            // Add base cheese price based on size
            switch (size) {
                case "4":
                    this.price += 0.75; // Price for 4" cheese
                    break;
                case "8":
                    this.price += 1.50; // Price for 8" cheese
                    break;
                case "12":
                    this.price += 2.25; // Price for 12" cheese
                    break;
            }
        }
    }

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
        if (extraCheese) {
            // Add additional cheese cost based on size
            switch(size) {
                case "4":
                    this.price += 0.30; // Additional price for 4" cheese
                    break;
                case "8":
                    this.price += 0.60; // Additional price for 8" cheese
                    break;
                case "12":
                    this.price += 0.90; // Additional price for 12" cheese
                    break;
            }
        }
    }

    public void addRegularTopping(Topping topping) {
        regularToppings.add(topping);
    }
    public void addRegularSauce(Topping sauce) {
        regularSauces.add(sauce);
    }

    public String getSize() {
        return size;
    }

    public String getBreadType() {
        return breadType;
    }

    public String getMeat() {
        return meat;
    }

    public boolean isExtraMeat() {
        return extraMeat;
    }

    public String getCheese() {
        return cheese;
    }

    public boolean isExtraCheese() {
        return extraCheese;
    }

    public List<Topping> getRegularToppings() {
        return regularToppings;
    }

    public List<Topping> getRegularSauces() {
        return regularSauces;
    }

    @Override
    public double getPrice() {
        return price;
    }
    @Override
    public String getDescription() {
        return getSummary();
    }
    public String getSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Sandwich Summary:\n");
        summary.append("Size: " + size + "\n");
        summary.append("Bread: " + breadType + "\n");
        summary.append("Meat: " + meat + (extraMeat ? " (Extra Meat)" : "") + "\n");
        summary.append("Cheese: " + cheese + (extraCheese ? " (Extra Cheese)" : "") + "\n");
        summary.append("Toppings: \n");
        summary.append("Sauces: \n");
        for (Topping t : regularToppings) {
            summary.append("- " + t.getName() + "\n");
        }
        for (Topping s : regularSauces) {
            summary.append("- " + s.getName() + " \n");
        }
        summary.append("Total Price: $" + String.format("%.2f", price));
        return summary.toString();
    }

}

