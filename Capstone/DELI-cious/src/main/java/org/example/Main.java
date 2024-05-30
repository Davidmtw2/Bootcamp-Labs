package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                HomeScreen homeScreen = new HomeScreen(new OrderManager(), scanner);
                running = homeScreen.display();  // This method needs to return a boolean
            }
        }  // Scanner automatically closes here
    }
}
