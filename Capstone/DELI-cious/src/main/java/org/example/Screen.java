package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public interface Screen {
    boolean display();
}

class HomeScreen implements Screen {
    private final OrderManager orderManager;
    private final Scanner scanner;

    public HomeScreen(OrderManager orderManager, Scanner scanner) {
        this.orderManager = orderManager;
        this.scanner = scanner;
    }

    @Override
    public boolean display() {
            System.out.println("\nWelcome to DELI-cious!");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            System.out.print("Please select an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    orderManager.startNewOrder();
                    OrderScreen orderScreen = new OrderScreen(orderManager, scanner);
                    orderScreen.display();
                    break;
                case "0":
                    System.out.println("Exiting...");
                    return false;

                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        return true;
    }
}

class OrderScreen implements Screen {
    private final OrderManager orderManager;
    private final Scanner scanner;

    public OrderScreen(OrderManager orderManager, Scanner scanner) {
        this.orderManager = orderManager;
        this.scanner = scanner;
    }

    @Override
    public boolean display() {
        boolean ordering = true;
        while (ordering) {
            System.out.println("--------------------------------------------------");
            System.out.println("\nOrder Menu:");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.println("--------------------------------------------------");
            System.out.print("Please select an option: ");
            String input = scanner.nextLine();
            System.out.println("\n--------------------------------------------------");



            switch (input) {
                case "1":
                    AddItemScreen addItemScreenSandwich = new AddItemScreen(orderManager, "1",scanner);
                    addItemScreenSandwich.display();
                    break;
                case "2":
                    AddItemScreen addItemScreenDrink= new AddItemScreen(orderManager, "2", scanner);
                    addItemScreenDrink.display();
                    break;
                case "3":
                    AddItemScreen addItemScreenChips = new AddItemScreen(orderManager,"3", scanner);
                    addItemScreenChips.display();
                    break;
                case "4":
                    orderManager.finalizeOrder();
                    ordering = false;
                    break;
                case "0":
                    System.out.println("Order canceled.");
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
        scanner.close();
        return ordering;
    }
}

class AddItemScreen implements Screen {
    private final OrderManager orderManager;
    private final String itemType;
    private final Scanner scanner;

    public AddItemScreen(OrderManager orderManager, String itemType, Scanner scanner) {
        this.orderManager = orderManager;
        this.itemType = itemType;
        this.scanner = scanner;
    }

    @Override
    public boolean display() {
        System.out.println("Adding Item to Order: " + itemType);


        switch (itemType) {
            case "1": // Add Sandwich
                System.out.println("--------------------------------------------------");
                System.out.println("Enter details for the Sandwich...");
                // Gather sandwich details from user
                Sandwich sandwich = createSandwich(scanner);
                orderManager.addItemToOrder(sandwich);
                break;
            case "2": // Add Drink
                System.out.println("--------------------------------------------------");
                System.out.println("Enter details for the Drink...");
                // Gather drink details from user
                Drink drink = createDrink(scanner);
                orderManager.addItemToOrder(drink);
                break;
            case "3": // Add Chips
                System.out.println("--------------------------------------------------");
                System.out.println("Enter details for the Chips...");
                // Gather chips details from user
                Chips chips = createChips(scanner);
                orderManager.addItemToOrder(chips);
                break;
            default:
                System.out.println("--------------------------------------------------");
                System.out.println("Unknown item type.");
                break;
        }
        System.out.println("--------------------------------------------------");
        System.out.println("Item added to order.");
        return false;
    }

    private Sandwich createSandwich(Scanner scanner) {
        System.out.println("Create your custom sandwich!");

        // Select size
        System.out.println("Choose sandwich size (4\", 8\", 12\"): ");
        String size = scanner.nextLine();
        Sandwich sandwich = new Sandwich(size);

        // Select bread type
        System.out.println("Choose bread type (white, wheat, rye, wrap): ");
        String breadType = scanner.nextLine();
        sandwich.setBreadType(breadType);

        // Select meat
        System.out.println("Choose meat (steak, ham, salami, roast beef, chicken, bacon): ");
        String meat = scanner.nextLine();
        sandwich.setMeat(meat);

        // Extra meat option
        System.out.println("Would you like extra meat? (yes/no): ");
        boolean extraMeat = scanner.nextLine().trim().equalsIgnoreCase("yes");
        sandwich.setExtraMeat(extraMeat);

        // Select cheese
        System.out.println("Choose cheese (american, provolone, cheddar, swiss): ");
        String cheese = scanner.nextLine();
        sandwich.setCheese(cheese);

        // Extra cheese option
        System.out.println("Would you like extra cheese? (yes/no): ");
        boolean extraCheese = scanner.nextLine().trim().equalsIgnoreCase("yes");
        sandwich.setExtraCheese(extraCheese);

        // select toppings
        String[] availableToppings = {"lettuce", "peppers", "onions", "tomatoes", "jalapenos", "cucumbers", "pickles", "guacamole"};
        System.out.println("Available toppings: ");
        for (String topping : availableToppings) {
            System.out.println("- " + topping);
        }

        // Add toppings
        System.out.println("Select the toppings they would like (type 'done' to finish): ");
        String toppingInput;
        while (!(toppingInput = scanner.nextLine()).equalsIgnoreCase("done")) {
            if (Arrays.asList(availableToppings).contains(toppingInput.toLowerCase())) {
                Topping topping = new Topping(toppingInput, size);
                sandwich.addRegularTopping(topping);
                System.out.println("Add another topping or type 'done':");
            } else {
                System.out.println("Invalid topping, please select from the list.");
            }
        }

        //select sauces
        String[] availableSauces = {"mayo", "mustard", "ketchup", "ranch", "thousand islands", "vinaigrette"};
        System.out.println("Available sauces: ");
        for (String sauces : availableSauces) {
            System.out.println("- " + sauces);
        }

        // Add sauces
        System.out.println("Select the toppings they would like (type 'done' to finish): ");
        String sauceInput;
        while (!(sauceInput = scanner.nextLine()).equalsIgnoreCase("done")) {
            if (Arrays.asList(availableSauces).contains(sauceInput.toLowerCase())) {
                Topping sauces = new Topping(sauceInput, size);
                sandwich.addRegularTopping(sauces);
                System.out.println("Add another sauces or type 'done':");
            } else {
                System.out.println("Invalid sauces, please select from the list.");
            }
        }

        return sandwich;
    }
    private Drink createDrink(Scanner scanner) {
        System.out.println("Choose your drink flavor (Cola, Lemon, Orange):");
        String flavor = scanner.nextLine();

        System.out.println("Choose your drink size (Small, Medium, Large):");
        String size = scanner.nextLine();

        // Example: setting a base price for simplicity
        double price = size.equalsIgnoreCase("Small") ? 2.00 : size.equalsIgnoreCase("Medium") ? 2.50 : 3.00;

        return new Drink(price, flavor + " " + size, size, flavor) {
            @Override
            public String getDescription() {
                return "";
            }
        };
    }
    private Chips createChips(Scanner scanner) {
        System.out.println("Choose your chip type (Regular, BBQ, Salt & Vinegar):");
        String type = scanner.nextLine();

        // Example: setting a fixed price for simplicity
        double price = 1.50; // Assuming a standard price for chips

        return new Chips(price, type + " Chips", type) {
            @Override
            public String getDescription() {
                return "";
            }
        };
    }

}
