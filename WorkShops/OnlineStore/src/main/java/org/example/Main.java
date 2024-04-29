package org.example;

public class Main {
    public static void main(String[] args) {
        Screens screens = new Screens("src/main/resources/products.csv");
        screens.displayMainMenu();
    }
}