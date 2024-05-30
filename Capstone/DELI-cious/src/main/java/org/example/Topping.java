package org.example;

public class Topping {
    private String name;
    private double extraCost;

    public Topping(String name, String size) {
        this.name = name;
//        setExtraCost(size);
    }

//    private void setExtraCost(String size) {
//        // Set the extra cost based on the size of the sandwich
//        switch(size) {
//            case "4\"":
//                this.extraCost = 0.30;
//                break;
//            case "8\"":
//                this.extraCost = 0.60;
//                break;
//            case "12\"":
//                this.extraCost = 0.90;
//                break;
//        }
//    }

    public String getName() {
        return name;
    }

    public double getExtraCost() {
        return extraCost;
    }








//    private String type;
//    private String name;
//    private boolean isExtra;
//    private double price;
//
//    public Topping(String type, String name, boolean isExtra, double price) {
//        this.type = type;
//        this.name = name;
//        this.isExtra = isExtra;
//        this.price = price;
//    }
//
//    public boolean isPremium() {
//        return "premium".equalsIgnoreCase(type);
//    }
//
//    public double getPrice() {
//        return price;
//    }
}
