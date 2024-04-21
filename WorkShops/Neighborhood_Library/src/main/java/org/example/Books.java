package org.example;

public class Books {
    private final int id;
    private final String isbn;
    private final String title;
    private boolean isCheckedOut;
    private String checkedOutTo;
    private String checkedOutOn;

    // Constructor


    public Books(int id, String isbn, String title){
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.isCheckedOut = false;
        checkedOutTo = "";
        checkedOutOn = "";
    }


    // Getters and setters for the properties
    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }
    public String getCheckedOutOn() {
        return checkedOutOn;
    }

    // Method to check out the book
    public void checkOut(String name, String date) {
        if (!this.isCheckedOut) {
            this.isCheckedOut = true;
            this.checkedOutTo = name;
            this.checkedOutOn = date;
            System.out.println(this.title + " has been checked out to " + name + " on " + date);
        } else {
            System.out.println("This book is already checked out.");
        }
    }

    // Method to check in the book
    public void checkIn() {
        if (this.isCheckedOut) {
            this.isCheckedOut = false;
            this.checkedOutTo = "";
            System.out.println(this.title + " has been checked in.");
        } else {
            System.out.println("This book is not checked out.");
        }
    }
}
