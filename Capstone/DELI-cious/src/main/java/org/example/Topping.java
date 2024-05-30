package org.example;

public class Topping {
    private String name;
    private double extraCost;

    public Topping(String name, String size) {
        this.name = name;
//        setExtraCost(size);
    }

    public String getName() {
        return name;
    }

    public double getExtraCost() {
        return extraCost;
    }
}
