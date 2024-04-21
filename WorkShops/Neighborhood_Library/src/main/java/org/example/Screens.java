package org.example;
import java.util.Scanner;

public class Screens {
    public static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();

    public static void mainScreen() {

        while (true) {

            System.out.println("\n1. Show Available Books");
            System.out.println("2. Show Checked Out Books");
            System.out.println("3. Check Out a Book");
            System.out.println("4. Check In a Book");
            System.out.println("5. Exit");
            System.out.print("Please choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    library.showAvailableBooks();
                    break;
                case "2":
                    library.showCheckedOutBooks();
                    break;
                case "3":
                    checkOutProcess();
                    break;
                case "4":
                    checkInProcess();
                    break;
                case "5":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    private static void checkOutProcess() {
        library.showAvailableBooks();
        System.out.print("Enter the ID of the book to check out: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter today's date (mm-dd-yyyy): ): ");
        String date = scanner.nextLine();
        library.checkOutBook(bookId, name, date);

    }



    private static void checkInProcess() {
        library.showCheckedOutBooks();
        System.out.print("Enter the ID of the book to check in: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        library.checkInBook(bookId);

    }


}
