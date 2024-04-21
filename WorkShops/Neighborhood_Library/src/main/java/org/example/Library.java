package org.example;

import static org.example.Screens.scanner;

public class Library {
    
    private Books[] books;
    private int bookCount;

    public Library() {
        books = new Books[20];
        bookCount = 0;
        initializeLibrary();
    }
    private void initializeLibrary() {
        books[bookCount++] = new Books(1, "9780743273565", "The Great Gatsby");
        books[bookCount++] = new Books(2, "9780439023481", "The Hunger Games");
        books[bookCount++] = new Books(3, "9780141439600", "Pride and Prejudice");
        books[bookCount++] = new Books(4, "9780679783268", "1984");
        books[bookCount++] = new Books(5, "9780316769488", "The Catcher in the Rye");
        books[bookCount++] = new Books(6, "9780451524935", "To Kill a Mockingbird");
        books[bookCount++] = new Books(7, "9780743273565", "The Great Gatsby");
        books[bookCount++] = new Books(8, "9780439023481", "The Hunger Games");
        books[bookCount++] = new Books(9, "9780141439600", "Pride and Prejudice");
        books[bookCount++] = new Books(10, "9780679783268", "1984");
        books[bookCount++] = new Books(11, "9780316769488", "The Catcher in the Rye");
        books[bookCount++] = new Books(12, "9780451524935", "To Kill a Mockingbird");
        books[bookCount++] = new Books(13, "9780743273565", "The Great Gatsby");
        books[bookCount++] = new Books(14, "9780439023481", "The Hunger Games");
        books[bookCount++] = new Books(15, "9780141439600", "Pride and Prejudice");
        books[bookCount++] = new Books(16, "9780679783268", "1984");
        books[bookCount++] = new Books(17, "9780316769488", "The Catcher in the Rye");
        books[bookCount++] = new Books(18, "9780451524935", "To Kill a Mockingbird");
        books[bookCount++] = new Books(19, "9781501129742", "The Glass Castle");
        books[bookCount++] = new Books(20, "9780553380163", "Into the Wild");
    }
    public void showAvailableBooks() {

        System.out.println("\n-----Currently Available books-----");
        for (int i = 0; i < bookCount; i++) {
            if (!books[i].isCheckedOut()) {
                System.out.println("ID: " + books[i].getId() + ", ISBN: " + books[i].getIsbn() + ", Title: " + books[i].getTitle());
            }

        }
        System.out.println("-----------------------------------------------------------------------------------");



    }
    public void showCheckedOutBooks() {
        System.out.println("-----Currently Checked Out books-----");
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isCheckedOut()) {
                System.out.println("ID: " + books[i].getId() + ", ISBN: " + books[i].getIsbn() + ", Title: " + books[i].getTitle() + ", Checked out to: " + books[i].getCheckedOutTo() + ", Checked out on: " + books[i].getCheckedOutOn());
            }
        }
        System.out.println("-----------------------------------------------------------------------------------");


    }
    public void checkOutBook(int bookId, String name, String date) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId() == bookId && !books[i].isCheckedOut()) {
                books[i].checkOut(name, date);
                return;
            }
        }
        System.out.println("Book ID not found or already checked out.");
    }
    public void checkInBook(int bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId() == bookId && books[i].isCheckedOut()) {
                books[i].checkIn();
                return;
            }
        }
        System.out.println("Book ID not found or not checked out.");
    }
}
