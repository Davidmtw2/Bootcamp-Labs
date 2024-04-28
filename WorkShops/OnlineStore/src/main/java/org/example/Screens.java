package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Screens {
    private final List<Product> products;
    private final Scanner scanner;
    private Map<Product, Integer> cart;

    public Screens(String filePath) {
        products = loadProducts(filePath);
        scanner = new Scanner(System.in);
        cart = new LinkedHashMap<>();
    }

    private List<Product> loadProducts(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();  // Skip the header line if there is one
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                try {
                    Product product = new Product(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]);
                    products.add(product);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
    public void displayMainMenu(){
        boolean running = true;
        while(running){
            System.out.println("Welcome to the our Online Store!");
            System.out.println("1. Display Products");
            System.out.println("2. Display Cart");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            System.out.println("---------------------------------------------");
            switch(input){
                case "1":
                    displayProducts();
                    break;
                case "2":
                    displayCart();
                    break;
                case "3":
                    running = false;
                    System.out.println("Thank you for using our Online Store!");
                    System.out.println("---------------------------------------------");
                    break;
                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }
    public void displayProducts(){
        System.out.println("List of Products:");
        for(Product product : products){
            System.out.println(product);
        }
        System.out.println("---------------------------------------------");
        System.out.println("1. Add a product to your cart");
        System.out.println("2. Search or filter the list of products");
        System.out.println("3. Go back to the main menu");
        System.out.print("Enter your choice: ");


        String input = scanner.nextLine();
        System.out.println("--------------------------------------------");
        switch(input){
            case "1":
                addToCart();
                break;
            case "2":
                searchOrFilterProducts();
                break;
            case "3":
                return;
            default:
                System.out.println("Invalid option, please try again");
        }
    }
    private void displayCart(){
        if(cart.isEmpty()){
            System.out.println("Your cart is empty");
            System.out.println("---------------------------------------------");
        }
        else {
            System.out.println("\nItems in Your Cart:");
            cart.forEach((product, quantity) -> System.out.println(quantity + " X " + product.getName() + " @ " + product.getPrice() + " each"));
            System.out.println("Total: " + String.format("%.2f",calculateTotal()));
            System.out.println("---------------------------------------------");

            System.out.println("1. Remove an item");
            System.out.println("2. Checkout");
            System.out.println("3. Go back to the main menu");
            System.out.print("Enter your choice: ");


            String input = scanner.nextLine();
            System.out.println("---------------------------------------------");
            switch(input){
                case "1":
                    removeFromCart();
                    break;
                case "2":
                    checkout();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
    private double calculateTotal(){
        return cart.entrySet().stream().mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue()).sum();
    }
    private void addToCart() {
        boolean continueShopping = true;
        while (continueShopping) {
            System.out.println("Enter the name or the SKU of the product you want to add to your cart:");
            String productIdentifier = scanner.nextLine();
            Product selectedProduct = null;

            // Search for the product by name or SKU
            for (Product product : products) {
                if (product.getName().equalsIgnoreCase(productIdentifier) || product.getSku().equalsIgnoreCase(productIdentifier)) {
                    selectedProduct = product;
                    break;
                }
            }

            if (selectedProduct == null) {
                System.out.println("Product not found. Please try again.");
                continue;  // Skip the rest of the loop and ask for another product or SKU
            }

            System.out.println("Enter the quantity you want to add:");
            int quantity;
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity <= 0) {
                    System.out.println("Please enter a positive number for quantity.");
                    continue;  // Skip the rest of the loop and ask for another product
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity. Please enter a number.");
                continue;  // Skip the rest of the loop and ask for another product
            }

            // Add to cart or update quantity
            cart.merge(selectedProduct, quantity, Integer::sum);
            System.out.println(quantity + " x " + selectedProduct.getName() + " added to your cart.");
            System.out.println("---------------------------------------------");

            // Ask if they want to add more items
            System.out.println("Would you like to add more items to your cart? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                continueShopping = false;  // Exit the loop and return to the main menu
            }
        }
    }
    private void searchOrFilterProducts() {
        System.out.println("Enter search criteria: \n1. By Name or Sku\n2. By Price\n3. By Department");
        System.out.print("Choose your option: ");
        String option = scanner.nextLine();
        List<Product> filteredProducts;
        System.out.println("---------------------------------------------");
        switch (option) {
            case "1":
                System.out.println("Choose the attribute to search by: \n1. Name\n2. SKU");
                System.out.print("Enter your choice: ");
                String searchType = scanner.nextLine();

                switch (searchType) {
                    case "1":  // Search by Name
                        System.out.print("Enter the product name to search for: ");
                        String name = scanner.nextLine().toLowerCase();
                        filteredProducts = products.stream()
                                .filter(product -> product.getName().toLowerCase().contains(name))
                                .collect(Collectors.toList());
                        break;
                    case "2":  // Search by SKU
                        System.out.print("Enter the SKU to search for: ");
                        String sku = scanner.nextLine().toLowerCase();
                        filteredProducts = products.stream()
                                .filter(product -> product.getSku().toLowerCase().equals(sku))
                                .collect(Collectors.toList());
                        break;
                    default:
                        System.out.println("Invalid search type. Please enter 1 for Name or 2 for SKU.");
                        return;
                }
                break;
            case "2":
                System.out.print("Enter the minimum price: ");
                double minPrice = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter the maximum price: ");
                double maxPrice = Double.parseDouble(scanner.nextLine());
                if (minPrice > maxPrice) {
                    System.out.println("Minimum price should not exceed maximum price. Please try again.");
                    return;
                }
                filteredProducts = products.stream().filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice).collect(Collectors.toList());
                break;
            case "3":
                System.out.print("Enter the department: ");
                String department = scanner.nextLine().toLowerCase();
                filteredProducts = products.stream().filter(product -> product.getDepartment().toLowerCase().equals(department)).collect(Collectors.toList());
                break;
            default:
                System.out.println("Invalid option, please try again.");
                return;
        }
        if (filteredProducts.isEmpty()){
            System.out.println("No products found the given criteria.");
        }
        else {
            System.out.println("Matching Products:");
            filteredProducts.forEach(System.out::println);
            System.out.println("Would you like to add any of these items to your cart? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equalsIgnoreCase("yes")) {
                addToCartFromSearch(filteredProducts);
            }
        }
    }
    private void addToCartFromSearch(List<Product> filteredProducts) {
        System.out.print("Enter the name or the SKU of the product you want to add to your cart: ");
        String productIdentifier = scanner.nextLine();
        Optional<Product> productOptional = filteredProducts.stream()
                .filter(product -> product.getName().equalsIgnoreCase(productIdentifier) ||
                        product.getSku().equalsIgnoreCase(productIdentifier)).findFirst();

        if (!productOptional.isPresent()) {
            System.out.println("Product not found in the filtered list. Please try again.");
            return;
        }

        Product selectedProduct = productOptional.get();
        System.out.print("Enter the quantity you want to add: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
            if (quantity <= 0) {
                System.out.println("Please enter a positive number for quantity.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Please enter a number.");
            return;
        }

        cart.merge(selectedProduct, quantity, Integer::sum);
        System.out.println(quantity + " x " + selectedProduct.getName() + " added to your cart.");
    }

    private void removeFromCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is currently empty.");
            return;
        }

        System.out.print("Enter the name or SKU of the product you want to remove from your cart: ");
        String identifier = scanner.nextLine().trim();

        // Find the product in the cart using name or SKU
        Optional<Map.Entry<Product, Integer>> entryToRemove = cart.entrySet().stream()
                .filter(entry -> entry.getKey().getName().equalsIgnoreCase(identifier) ||
                        entry.getKey().getSku().equalsIgnoreCase(identifier))
                .findFirst();

        if (!entryToRemove.isPresent()) {
            System.out.println("Product not found in your cart.");
            return;
        }

        Product product = entryToRemove.get().getKey();
        System.out.print("Enter the quantity to remove (current quantity " + entryToRemove.get().getValue() + "): ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
            if (quantity <= 0) {
                System.out.println("Please enter a positive number.");
                return;
            }
            if (quantity > entryToRemove.get().getValue()) {
                System.out.println("You cannot remove more than what is in the cart.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        // Update or remove the product in the cart
        cart.computeIfPresent(product, (key, val) -> val - quantity <= 0 ? null : val - quantity);
        System.out.println("Removed " + quantity + " x " + product.getName() + " from your cart.");
    }

    private void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add some products before checkout.");
            return;
        }

        double total = calculateTotal();
        System.out.println("Your total is: $" + String.format("%.2f", total));
        System.out.print("Enter the amount you're paying: $");

        double payment;
        try {
            payment = Double.parseDouble(scanner.nextLine());
            if (payment < total) {
                System.out.println("Insufficient payment. Please provide at least the total amount.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric value.");
            return;
        }

        double change = payment - total;
        System.out.println("Payment received. Your change is $" + String.format("%.2f", change));
        System.out.println("Thank you for your purchase!");

        // Record the time of purchase
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Generate a receipt and save it to a file
        try (PrintWriter out = new PrintWriter(new FileWriter("src/main/resources/receipts.csv", true))) {
            out.println("Receipt Time: " + formatter.format(now));
            cart.forEach((product, quantity) ->
                    out.println(quantity + " x " + product.getName() + " @ $" + String.format("%.2f", product.getPrice()) + " each"));
            out.println("Total: $" + String.format("%.2f", total));
            out.println("Paid: $" + String.format("%.2f", payment));
            out.println("Change: $" + String.format("%.2f", change));
            out.println("Thank you for using our Online Store!");
            System.out.println("---------------------------------------------");
            System.out.println("Thank you for using our Online Store!");
            System.out.println("---------------------------------------------");
            out.println();  // Add an empty line for better separation between receipts
        } catch (IOException e) {
            System.out.println("An error occurred while writing the receipt. Please check your file system permissions.");
        }

        // Clear the cart and end
        cart.clear();
        System.out.println("Exiting the store...");
        System.exit(0);
    }
}
